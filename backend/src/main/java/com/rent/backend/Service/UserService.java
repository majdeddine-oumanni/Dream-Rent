package com.rent.backend.Service;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Mappers.UserMapper;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDTO create(UserDTO dto){
        User user = mapper.toEntity(dto);
        User savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }

    public UserDTO update(Long id, UserDTO dto){
        User user = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found"));
        
        user.setBanned(dto.isBanned());
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());

        User savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }


}
