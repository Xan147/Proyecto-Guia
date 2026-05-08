package com.project.shop.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.shop.Dtos.UsuarioDTO;
import com.project.shop.Mapper.UsuarioMapper;
import com.project.shop.Models.Usuario;
import com.project.shop.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    //crear un usuario
    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + dto.getEmail());
        }
        Usuario usuario = usuarioMapper.toUsuario(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        return usuarioMapper.toUsuarioDto(usuarioRepository.save(usuario));
    }

    //Buscar por id
    @Override
    public UsuarioDTO buscarPorId(String id) {
        return usuarioMapper.toUsuarioDto(findPorId(id));
    }

    //Buscar por email
    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toUsuarioDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    //Obtener todos los usuarios
    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioDto)
                .collect(Collectors.toList());
    }

    //Actualizar
    @Override
    public UsuarioDTO actualizar(String id, UsuarioDTO dto) {
        Usuario usuario = findPorId(id);
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRoles() != null) {
            usuario.setRoles(dto.getRoles());
        }
        return usuarioMapper.toUsuarioDto(usuarioRepository.save(usuario));
    }

    //Eliminar
    @Override
    public void eliminar(String id) {
        findPorId(id);
        usuarioRepository.deleteById(id);
    }

    //Funcion para utilizar el metodo de repository evitando repetir la excepcion
    private Usuario findPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}
