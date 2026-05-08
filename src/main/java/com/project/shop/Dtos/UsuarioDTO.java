package com.project.shop.Dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shop.Models.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    //Puede recibir la contra mas no enviarla devuelta, asi nos evitamos 2 dtos y solo hacemos 1
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private String telefono;

    private Set<Roles> roles;
}
