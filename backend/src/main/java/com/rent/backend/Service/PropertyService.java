package com.rent.backend.Service;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Mappers.PropertyMapper;
import com.rent.backend.Model.Owner;
import com.rent.backend.Model.Property;
import com.rent.backend.Model.PropertyType;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.PropertyRepository;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;
    private final UserRepository userRepository;

    public PropertyService(PropertyRepository repository, PropertyMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public PropertyDTO create(PropertyDTO dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Owner owner = (Owner) userRepository.findByEmail(email);
        Property property = mapper.toEntity(dto);
        property.setOwner(owner);
        return mapper.toDTO(repository.save(property));
    }

    public PropertyDTO update(Long id, PropertyDTO dto){
        Property property = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Property not found"));

        property.setAvailability(dto.isAvailability());
        property.setCountry(dto.getCountry());
        property.setCity(dto.getCity());
        property.setDescription(dto.getDescription());
        property.setPropertyType(dto.getPropertyType());
        property.setRoomsNumber(dto.getRoomsNumber());
        property.setArea(dto.getArea());
        property.setBathroomsNumber(dto.getBathroomsNumber());

        Property savedProperty = repository.save(property);

        return mapper.toDTO(savedProperty);
    }

    public List<PropertyDTO> getAllProperties(){
        List<Property> properties = repository.findAll();
        return mapper.toDTOs(properties);
    }

    public void delete(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Owner owner = (Owner) userRepository.findByEmail(email);

        Property property = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwner().getId().equals(owner.getId())) {
            throw new RuntimeException("You are not allowed to delete this property");
        }

        repository.deleteById(id);
    }


    public PropertyDTO getPropertyById(Long id){
        Property property = repository.findById(id).
                orElseThrow(()-> new RuntimeException("Property not found"));
        return mapper.toDTO(property);
    }

    public List<PropertyDTO> getPropertiesByOwnerId(Long id){
        List<Property> properties = repository.findAllByOwnerId(id);
        return mapper.toDTOs(properties);
    }

    public List<PropertyDTO> getPropertiesByCityName(String cityName){
        List<Property> properties = repository.findAllByCity(cityName);
        return mapper.toDTOs(properties);
    }

    public List<PropertyDTO> getPropertiesByCountryName(String country){
        List<Property> properties = repository.findAllByCountry(country);
        return mapper.toDTOs(properties);
    }

    public List<PropertyDTO> findPropertiesByPropertyType(PropertyType propertyType){
        List<Property> properties = repository.findAllByPropertyType(propertyType);
        return mapper.toDTOs(properties);
    }

}
