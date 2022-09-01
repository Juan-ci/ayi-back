package org.example.commons;

import org.modelmapper.ModelMapper;

public class MapperSingleton {

    private static ModelMapper mapper;

    private MapperSingleton() {

    }

    public static ModelMapper getMapperInstance() {
        if(mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }
}
