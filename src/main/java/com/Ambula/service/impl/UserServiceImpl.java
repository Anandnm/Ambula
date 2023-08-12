package com.Ambula.service.impl;

import com.Ambula.entity.User;
import com.Ambula.exception.RessourceNotFoundException;
import com.Ambula.payload.UserDto;
import com.Ambula.repository.UserRepository;
import com.Ambula.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setRole(userDto.getRole());
       User save =userRepository.save(user);

       UserDto dto = new UserDto();
       dto.setId(save.getId());
       dto.setUsername(save.getUsername());
       dto.setRole(save.getRole());

        return dto;
    }

    @Override
    public List<UserDto> getAllUser() {
     List<UserDto> collect = userRepository.findAll().stream().map(user -> mapToDto(user)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Post Not Found By Id" + id));
      return   mapToDto(user);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Post Not Found By Id" + id));
        User users = mapToEntity(userDto);
        users.setId(id);
        User save = userRepository.save(users);
       return mapToDto(save);

    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Post Not Found By Id" + id));
        userRepository.delete(user);
    }

    UserDto mapToDto(User user){
       return modelMapper.map(user,UserDto.class);
    }
    User mapToEntity(UserDto userDto){
      return   modelMapper.map(userDto,User.class);
    }
}
