package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioCreateDto {

    @NotBlank
    @Email(message = "formato do e-mail está inválido", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String username;
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres", max = 6)
    private String password;

}
