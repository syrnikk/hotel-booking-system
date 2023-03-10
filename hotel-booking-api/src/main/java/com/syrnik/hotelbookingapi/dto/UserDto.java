package com.syrnik.hotelbookingapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private List<String> roles;
}
