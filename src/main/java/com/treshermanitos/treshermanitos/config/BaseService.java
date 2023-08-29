package com.treshermanitos.treshermanitos.config;


public interface BaseService<T, TDto> {
    
    TDto getById(Long idLong);

    T getByIdAllEntity(Long idLong);

    TDto createOne(TDto body);

    TDto updateById(Long id, TDto body);

    void deleteById(Long id);
}
