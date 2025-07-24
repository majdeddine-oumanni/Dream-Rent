package com.rent.backend.Controller;

import com.rent.backend.DTO.PropertyDTO;
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

    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    @PostMapping
    public PropertyDTO createProperty(@RequestBody PropertyDTO dto) {
        return service.create(dto);
    }

    @PreAuthorize("hasRole('OWNER')")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    @GetMapping("/owner/{ownerId}")  // Better path variable naming
    public List<PropertyDTO> getPropertiesByOwner(@PathVariable("ownerId") Long ownerId) {
        return service.getPropertiesByOwnerId(ownerId);
    }

    @GetMapping("/search/city")  // Better for search queries
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
}