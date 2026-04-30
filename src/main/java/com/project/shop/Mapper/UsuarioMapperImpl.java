package com.project.shop.Mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.project.shop.Dtos.UsuarioRequestDTO;
import com.project.shop.Dtos.UsuarioResponseDTO;
import com.project.shop.Models.Roles;
import com.project.shop.Models.Usuario;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioRequestDTO dto) {
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
    public UsuarioResponseDTO toUsuarioDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setTelefono(usuario.getTelefono());
        response.setRoles(usuario.getRoles());
        return response;
    }
}
