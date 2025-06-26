package com.rent.backend.Service;

import com.rent.backend.Mappers.PropertyMapper;
import com.rent.backend.Repositories.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;

    public PropertyService(PropertyRepository repository, PropertyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    
}
