package com.rent.backend.Controller;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Model.PropertyType;
import com.rent.backend.Service.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin("*")
public class PropertyController {
    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public PropertyDTO createProperty(@RequestBody PropertyDTO dto) {
        return service.create(dto);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    @PutMapping("/{id}")
    public PropertyDTO updateProperty(@PathVariable Long id, @RequestBody PropertyDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public List<PropertyDTO> getAllProperties() {
        return service.getAllProperties();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public PropertyDTO getPropertyById(@PathVariable Long id) {
        return service.getPropertyById(id);
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/owner/{ownerId}")
    public List<PropertyDTO> getPropertiesByOwner(@PathVariable Long ownerId) {
        return service.getPropertiesByOwnerId(ownerId);
    }

    @GetMapping("/search/city")
    public List<PropertyDTO> searchPropertiesByCity(@RequestParam String city) {
        return service.getPropertiesByCityName(city);
    }

    @GetMapping("/search/country")
    public List<PropertyDTO> searchPropertiesByCountry(@RequestParam String country) {
        return service.getPropertiesByCountryName(country);
    }

    @GetMapping("/search/type")
    public List<PropertyDTO> searchPropertiesByType(@RequestParam PropertyType type) {
        return service.findPropertiesByPropertyType(type);
    }

    @GetMapping("/search/price")
    public List<PropertyDTO> searchPropertiesByPrice(@RequestParam double price1, @RequestParam double price2) {
        return service.getPropertiesByPrice(price1, price2);
    }

    @GetMapping("/search/place")
    public List<PropertyDTO> findByPlace(@RequestParam String place){
        return service.findByPlace(place);
    }


    @GetMapping("/ownerOfProperty/{property_id}")
    public UserDTO getOwnerByPropertyId(@PathVariable Long property_id){
        return service.getOwnerByPropertyId(property_id);
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/ownersPropertiesNumber/{owner_id}")
    public Long ownersPropertiesNumber(@PathVariable Long owner_id){
        return service.ownersPropertiesNumber(owner_id);
    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/availableProperties/{owner_id}")
    public Long getAvailableProperties(@PathVariable Long owner_id){
        return service.getAvailablePropertiesNumber(owner_id);
    }
}