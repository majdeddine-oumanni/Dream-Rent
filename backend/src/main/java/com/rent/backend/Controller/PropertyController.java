package com.rent.backend.Controller;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Model.Property;
import com.rent.backend.Service.PropertyService;
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

    @PostMapping("/post")
    public PropertyDTO addProperty(@RequestBody PropertyDTO dto){
        return service.create(dto);
    }

    @PutMapping("/update/{id}")
    public PropertyDTO updateProperty(@PathVariable Long id, @RequestBody PropertyDTO dto){
        return service.update(id, dto);
    }

    @GetMapping("/get")
    public List<PropertyDTO> getAll(){
        return service.getAllProperties();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProperty(@PathVariable Long id){
        service.delete(id);
    }
}
