package com.project.shop.Mapper;

import com.project.shop.Dtos.UsuarioDTO;
import com.project.shop.Models.Usuario;

public interface UsuarioMapper {
    //De dto a modelo
    Usuario toUsuario(UsuarioDTO dto);
    //De modelo a dto
    UsuarioDTO toUsuarioDto(Usuario usuario);
}
