package com.project.shop.Mapper;

import com.project.shop.Dtos.UsuarioRequestDTO;
import com.project.shop.Dtos.UsuarioResponseDTO;
import com.project.shop.Models.Usuario;

public interface UsuarioMapper {
    //De dto a modelo
    Usuario toUsuario(UsuarioRequestDTO dto);
    //De modelo a dto
    UsuarioResponseDTO toUsuarioDto(Usuario usuario);
}
