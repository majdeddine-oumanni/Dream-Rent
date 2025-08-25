package com.rent.backend.Mappers;

import com.rent.backend.DTO.ReviewsDTO;
import com.rent.backend.Model.Reviews;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewsMapper {
    Reviews toEntity(ReviewsDTO dto);
    ReviewsDTO toDTO(Reviews reviews);
    List<ReviewsDTO> toDTOs(List<Reviews> reviews);
}
