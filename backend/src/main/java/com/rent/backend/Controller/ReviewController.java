package com.rent.backend.Controller;


import com.rent.backend.DTO.ReviewsDTO;
import com.rent.backend.Service.ReviewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {
    private final ReviewsService service;

    public ReviewController(ReviewsService service) {
        this.service = service;
    }
    @PostMapping("/{property_id}")
    public ReviewsDTO postReview(@PathVariable Long property_id, @RequestBody ReviewsDTO dto){
        return service.makeReview(property_id, dto);
    }

    @GetMapping("/property/{property_id}")
    public List<ReviewsDTO> findByProperty(@PathVariable Long property_id){
        return service.findAllByProperty(property_id);
    }
}
