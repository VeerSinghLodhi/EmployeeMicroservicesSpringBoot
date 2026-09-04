package com.example.AUTH.mapper;

import com.example.AUTH.dto.UserDto;
import com.example.AUTH.entity.User;

public class UserMapper {

    public static User toEntity(UserDto userDto){
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public static UserDto toDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
