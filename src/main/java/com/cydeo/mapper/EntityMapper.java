package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public EntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, S> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }


}
