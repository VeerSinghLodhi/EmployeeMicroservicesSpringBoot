package com.example.AUTH.service.impl;

import com.example.AUTH.dto.JwtTokenResponse;
import com.example.AUTH.dto.LoginRequest;
import com.example.AUTH.dto.UserDto;
import com.example.AUTH.entity.User;
import com.example.AUTH.mapper.UserMapper;
import com.example.AUTH.repository.UserRepository;
import com.example.AUTH.service.UserService;
import com.example.AUTH.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDto registerUser(UserDto userDto) {
        User newUser= UserMapper.toEntity(userDto);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return UserMapper.toDto(userRepository.save(newUser));
    }

    @Override
    public JwtTokenResponse login(LoginRequest loginRequest) {
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(token);
        jwtTokenResponse.setType("Bearer");
        jwtTokenResponse.setValidUntil(jwtUtil.extractExpiration(token).toString());
        return jwtTokenResponse;
    }
}
