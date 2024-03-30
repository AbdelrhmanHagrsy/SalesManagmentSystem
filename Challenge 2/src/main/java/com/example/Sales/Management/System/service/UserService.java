package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.jwtDto.UserRequestDto;
import com.example.Sales.Management.System.jwtDto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDto> getAllUser();

    public UserResponseDto createUser(UserRequestDto userRequestDto);
}