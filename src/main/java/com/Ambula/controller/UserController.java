package com.Ambula.controller;

import com.Ambula.entity.User;
import com.Ambula.payload.UserDto;
import com.Ambula.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ambula")
public class UserController {
    @Autowired
    private UserService userService;

    //http://localhost:8080/api/ambula
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping
    public List<UserDto> getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return allUser;

    }


    @PatchMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id){
        UserDto dto = userService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<UserDto>updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto){
        UserDto dto = userService.updateUser(id, userDto);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User Details Deleted",HttpStatus.OK);
    }
}
