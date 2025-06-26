package com.rent.backend.Controller;

import com.rent.backend.DTO.UserDTO;
import com.rent.backend.Model.User;
import com.rent.backend.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public UserDTO addUser(@RequestBody UserDTO dto){
        return service.create(dto);
    }
}
