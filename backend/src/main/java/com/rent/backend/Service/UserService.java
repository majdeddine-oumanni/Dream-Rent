package com.rent.backend.Service;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Mappers.UserMapper;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO update(Long id, UserDTO dto){
        User user = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found"));

        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCountry(dto.getCountry());

        User savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }

    public List<UserDTO> getAllUsers(){
        return mapper.toDTOs(repository.findAll());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    public UserDTO updateBanning(Long id, boolean isBanned){
        User user = repository.findById(id).
                orElseThrow(()-> new RuntimeException("user not found"));
        user.setBanned(isBanned);
        return mapper.toDTO(user);
    }
}
