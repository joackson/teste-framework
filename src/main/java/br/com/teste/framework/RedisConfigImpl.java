package br.com.teste.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RedisConfigImpl implements RedisConfig {
	
	RedisTemplate template; 

	public RedisConfigImpl() {
	}

	@Bean
	public RedisTemplate createTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate tmpl = new RedisTemplate<String, Object>();
		tmpl.setConnectionFactory(connectionFactory);
		template = tmpl;
		return tmpl;
	}


	public void setEntityType(Class<?> entityType){
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JacksonJsonRedisSerializer<>(entityType));
	}

	public <T> void setEntity(String key, T entity) {
		setEntityType(entity.getClass());
		template.opsForValue().set(key, entity);
	}


	public <T> T getEntity(Class<T> entityType, String key) {
		setEntityType(entityType);
		return (T) template.opsForValue().get(key);
	}

	public RedisTemplate getTemplate()  {
		return template;
	}

}