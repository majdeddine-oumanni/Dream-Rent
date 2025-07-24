package com.rent.backend.Repositories;

import com.rent.backend.Model.Property;
import com.rent.backend.Model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByOwnerId(Long id);

    List<Property> findAllByCity(String city);

    List<Property> findAllByCountry(String country);

    List<Property> findAllByPropertyType(PropertyType propertyType);
}
