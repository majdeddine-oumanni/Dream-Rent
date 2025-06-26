package com.rent.backend.Service;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Mappers.PropertyMapper;
import com.rent.backend.Model.Property;
import com.rent.backend.Repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;

    public PropertyService(PropertyRepository repository, PropertyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PropertyDTO create(PropertyDTO dto){
        Property property = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(property));
    }

    public PropertyDTO update(Long id, PropertyDTO dto){
        Property property = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Property not found"));

        property.setAvailability(dto.isAvailability());
        property.setLocation(dto.getLocation());
        property.setDescription(dto.getDescription());
        property.setType(dto.getType());
        property.setRoomsNumber(dto.getRoomsNumber());

        Property savedProperty = repository.save(property);

        return mapper.toDTO(savedProperty);
    }

    public List<PropertyDTO> getAllProperties(){
        List<Property> properties = repository.findAll();
        return mapper.toDTOs(properties);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
