package com.example.Sales.Management.System.jwtDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
//    private String password;
    private String aboutMe;
    private long  id;
}
