package com.project.shop.Mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.project.shop.Dtos.UsuarioDTO;
import com.project.shop.Models.Roles;
import com.project.shop.Models.Usuario;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioDTO dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .telefono(dto.getTelefono())
                .roles(dto.getRoles() != null ? dto.getRoles() : Set.of(Roles.ROLE_USER))
                .build();
    }

    @Override
    public UsuarioDTO toUsuarioDto(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .roles(usuario.getRoles())
                .build();
    }
}
