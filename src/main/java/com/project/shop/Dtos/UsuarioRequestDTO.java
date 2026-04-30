package com.project.shop.Dtos;

import java.util.Set;

import com.project.shop.Models.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private String telefono;

    private Set<Roles> roles;
}
