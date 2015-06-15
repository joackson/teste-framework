package br.com.teste.framework;

public interface RedisConfig {
	<T> void setEntity(String key, T entity);
	<T> T getEntity(Class<T> entityType, String key);
}