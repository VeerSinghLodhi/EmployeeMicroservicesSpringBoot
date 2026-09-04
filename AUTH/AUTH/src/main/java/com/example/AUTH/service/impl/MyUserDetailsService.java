package com.example.AUTH.service.impl;

import com.example.AUTH.entity.User;
import com.example.AUTH.exception.ResourceNotFoundException;
import com.example.AUTH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not found with username: "+username);
        }
        return new MyUserDetails(user.get());
    }
}
