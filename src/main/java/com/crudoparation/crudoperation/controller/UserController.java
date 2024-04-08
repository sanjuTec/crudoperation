package com.crudoparation.crudoperation.controller;

import com.crudoparation.crudoperation.dto.UserDTO;
import com.crudoparation.crudoperation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/getUsers")
    public List<UserDTO> getUser(){
        return userService.retriveUsers();
    }

    @PostMapping("/saveUser")
    public UserDTO saveUser(@RequestBody  UserDTO user_dto){
        return   userService.saveUser(user_dto);
    }

    @GetMapping("/getUserById/{userId}")
    public UserDTO getUserByUserId(@PathVariable int userId){
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/getUserByIdNemail/{userId}/{email}")
    public UserDTO getUserByUserIdNemail(@PathVariable int userId,@PathVariable String email){
        return userService.getUserByUserIdNemail(userId,email);
    }

    @PutMapping("/updateUserById/{userId}")
    public UserDTO updateUserById(@PathVariable  int userId, @RequestBody UserDTO updatedUserDTO){
        return userService.updateUserById(userId,updatedUserDTO);
    }
}