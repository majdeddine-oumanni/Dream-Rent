package com.rent.backend.Controller;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Model.User;
import com.rent.backend.Service.UserService;
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

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return service.update(id, dto);
    }

    @GetMapping("/get")
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        service.delete(id);
    }

    @PatchMapping("/isBanned/{id}")
    public UserDTO isBanned(@PathVariable Long id, @RequestParam boolean isBanned){
        return service.updateBanning(id, isBanned);
    }
}
