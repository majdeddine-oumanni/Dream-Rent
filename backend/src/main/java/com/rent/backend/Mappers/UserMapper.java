package com.rent.backend.Mappers;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDTO(User user);
    List<UserDTO> toDTOs(List<User> users);
}
