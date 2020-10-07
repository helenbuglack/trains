package com.train.service.converter;

public interface Converter<T, V>
{
	V convertToDTO (T t);

	T convertToEntity(V v);
}
