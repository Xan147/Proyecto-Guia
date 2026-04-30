package com.project.shop.Services;

import java.util.List;

import com.project.shop.Dtos.UsuarioRequestDTO;
import com.project.shop.Dtos.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO crear(UsuarioRequestDTO dto);

    UsuarioResponseDTO buscarPorId(String id);

    UsuarioResponseDTO buscarPorEmail(String email);

    List<UsuarioResponseDTO> listarTodos();

    UsuarioResponseDTO actualizar(String id, UsuarioRequestDTO dto);

    void eliminar(String id);
}
