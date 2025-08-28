package com.rent.backend.Controller;


import com.rent.backend.DTO.ReviewsDTO;
import com.rent.backend.Service.ReviewsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {
    private final ReviewsService service;

    public ReviewController(ReviewsService service) {
        this.service = service;
    }
    @PostMapping("/{id}")
    public ReviewsDTO postReview(@PathVariable Long id, @RequestBody ReviewsDTO dto){
        return service.makeReview(id, dto);
    }
}
