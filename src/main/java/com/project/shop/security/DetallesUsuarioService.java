package com.project.shop.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.shop.Models.Usuario;
import com.project.shop.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallesUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(
                        usuario.getRoles()
                                .stream()
                                .map(Enum::name)
                                .toArray(String[]::new)
                )
                .build();
    }
}