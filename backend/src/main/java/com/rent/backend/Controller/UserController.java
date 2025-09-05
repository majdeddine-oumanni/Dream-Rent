package com.rent.backend.Controller;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER', 'TENANT')")
    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return service.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        service.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/isBanned/{id}")
    public UserDTO isBanned(@PathVariable Long id, @RequestParam boolean isBanned){
        return service.updateBanning(id, isBanned);
    }

    //ask about this urgently
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @GetMapping("/totalNumber")
    public long getAllUsersNumber(){
        return service.getUsersNumber();
    }
}
