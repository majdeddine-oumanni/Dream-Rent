package com.rent.backend.Service;

import com.rent.backend.DTO.ReviewsDTO;
import com.rent.backend.Mappers.ReviewsMapper;
import com.rent.backend.Model.Property;
import com.rent.backend.Model.Reviews;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.PropertyRepository;
import com.rent.backend.Repositories.ReviewsRepository;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsMapper mapper;
    private final ReviewsRepository repository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public ReviewsService(ReviewsMapper mapper, ReviewsRepository repository, UserRepository userRepository, PropertyRepository propertyRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    public ReviewsDTO makeReview(Long property_id, ReviewsDTO dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        Property property = propertyRepository.findById(property_id).
                orElseThrow(()-> new RuntimeException("property not found!"));
        Reviews reviews = mapper.toEntity(dto);
        reviews.setProperty(property);
        reviews.setUser(user);
        Reviews savedReview = repository.save(reviews);
        return mapper.toDTO(savedReview);
    }

    public List<ReviewsDTO> findAllByProperty(Long propertyId){
        List<Reviews> reviews = repository.findAllByProperty_Id(propertyId);
        return mapper.toDTOs(reviews);
    }
    
}
