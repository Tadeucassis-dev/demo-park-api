package com.mballem.demo_park_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioCreateDto {

    private Long id;
    private String username;
    private String password;
}
