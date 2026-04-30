package com.project.shop.Dtos;

import java.util.Set;

import com.project.shop.Models.Roles;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private Set<Roles> roles;
}
