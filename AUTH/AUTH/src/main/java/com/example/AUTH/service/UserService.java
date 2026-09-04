package com.example.AUTH.service;

import com.example.AUTH.dto.JwtTokenResponse;
import com.example.AUTH.dto.LoginRequest;
import com.example.AUTH.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    JwtTokenResponse login(LoginRequest loginRequest);
}
