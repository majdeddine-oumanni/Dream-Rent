package com.rent.backend.Service;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Mappers.UserMapper;
import com.rent.backend.Model.Role;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public UserDTO update(Long id, UserDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = repository.findByEmail(email);

        if (loggedInUser.getRole().equals(Role.ADMIN) || loggedInUser.getId().equals(id)) {
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setCountry(dto.getCountry());
            user.setPhone(dto.getPhone());
            if (dto.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            User savedUser = repository.save(user);
            return mapper.toDTO(savedUser);
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this user");
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
    public UserDTO getUserById(Long id){
        User user = repository.findById(id).
                orElseThrow(()-> new RuntimeException("user not found"));
        return mapper.toDTO(user);
    }

    public long getUsersNumber(){
        return repository.count();
    }

    public long getUsersNumberByRole(Role role){
        return repository.getNumberOfUsersByRole(role);
    }
}
