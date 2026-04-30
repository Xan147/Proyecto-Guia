package com.project.shop.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.shop.Dtos.UsuarioRequestDTO;
import com.project.shop.Dtos.UsuarioResponseDTO;
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

    @Override
    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + dto.getEmail());
        }
        Usuario usuario = usuarioMapper.toUsuario(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        return usuarioMapper.toUsuarioDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO buscarPorId(String id) {
        return usuarioMapper.toUsuarioDto(findOrThrow(id));
    }

    @Override
    public UsuarioResponseDTO buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toUsuarioDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO actualizar(String id, UsuarioRequestDTO dto) {
        Usuario usuario = findOrThrow(id);
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

    @Override
    public void eliminar(String id) {
        findOrThrow(id);
        usuarioRepository.deleteById(id);
    }

    private Usuario findOrThrow(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}
