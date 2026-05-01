package com.project.shop.Services;

import java.util.List;

import com.project.shop.Dtos.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crear(UsuarioDTO dto);

    UsuarioDTO buscarPorId(String id);

    UsuarioDTO buscarPorEmail(String email);

    List<UsuarioDTO> listarTodos();

    UsuarioDTO actualizar(String id, UsuarioDTO dto);

    void eliminar(String id);
}
