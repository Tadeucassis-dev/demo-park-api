package com.mballem.demo_park_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioResponseDto {

    private String username;
    private String password;
    private String role;

    public void setRole(String role) {

    }
}


