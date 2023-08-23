package com.treshermanitos.treshermanitos.config;

import java.util.List;

public interface BaseService<T, TDto> {

    List<TDto> getAll();

    TDto getById(Long idLong);

    T getByIdAllEntity(Long idLong);

    TDto createOne(TDto body);

    TDto updateById(Long id, TDto body);

    Boolean deleteById(Long id);
}
