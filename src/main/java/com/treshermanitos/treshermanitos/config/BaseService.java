package com.treshermanitos.treshermanitos.config;

import java.util.List;

public interface BaseService<T, TDto> {

    List<TDto> getAll();

    TDto getById(Long idLong);

    void createOne(TDto body);

    TDto updateById(Long id, TDto body);

    TDto deleteById(Long id);
}
