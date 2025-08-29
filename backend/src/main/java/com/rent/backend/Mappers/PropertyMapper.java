package com.rent.backend.Mappers;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    Property toEntity(PropertyDTO dto);

    @Mapping(source = "id", target = "id")
    PropertyDTO toDTO(Property property);
    List<PropertyDTO> toDTOs(List<Property> properties);
}
