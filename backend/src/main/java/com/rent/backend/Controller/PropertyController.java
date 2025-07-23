package com.rent.backend.Controller;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Service.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
@CrossOrigin("*")
public class PropertyController {
    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    @PostMapping("/post")
    public PropertyDTO addProperty(@RequestBody PropertyDTO dto){
        return service.create(dto);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/update/{id}")
    public PropertyDTO updateProperty(@PathVariable Long id, @RequestBody PropertyDTO dto){
        return service.update(id, dto);
    }

    @GetMapping("/get")
    public List<PropertyDTO> getAll(){
        return service.getAllProperties();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    @DeleteMapping("/delete/{id}")
    public void deleteProperty(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/get/by/{id}")
    public PropertyDTO getPropertyById(@PathVariable Long id){
        return service.getPropertyById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    @GetMapping("/get/by/owner/{id}")
    public List<PropertyDTO> getPropertiesByOwnerId(@PathVariable Long id){
        return service.getPropertiesByOwnerId(id);
    }
}
