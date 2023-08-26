package com.treshermanitos.treshermanitos.config;


public interface BaseMapper <ENTITY, DTO>{
    ENTITY dtoToEntity(DTO dto);
    DTO entityToDto(ENTITY entity);
}