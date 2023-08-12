package com.Ambula.service;

import com.Ambula.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto getById(Long id);

    UserDto updateUser(long id, UserDto userDto);

    void deleteUser(long id);
}
