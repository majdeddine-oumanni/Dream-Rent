package com.rent.backend.Repositories;

import com.rent.backend.Model.Property;
import com.rent.backend.Model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByOwnerId(Long id);

    List<Property> findAllByCity(String city);

    List<Property> findAllByCountry(String country);

    List<Property> findAllByPropertyType(PropertyType propertyType);

    @Query(value = "SELECT COUNT(p) FROM Property p WHERE p.owner.id = :owner_id")
    Long ownersPropertiesNumber(Long owner_id);

    @Query(value = "SELECT COUNT(p) FROM Property p WHERE p.availability = true AND p.owner.id = :owner_id")
    Long availablePropertiesNumber(Long owner_id);

    @Query(value = "SELECT p FROM Property p WHERE p.price BETWEEN :price1 AND :price2")
    List<Property> getPropertiesByPrice(double price1, double price2);

    @Query(value = "SELECT p FROM Property p WHERE p.city = :place OR p.country = :place")
    List<Property> findByPlace(String place);
}
