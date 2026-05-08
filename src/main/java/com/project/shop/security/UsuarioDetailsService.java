package com.project.shop.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.shop.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> new User(
                        usuario.getEmail(),
                        usuario.getPassword(),
                        usuario.getRoles().stream()
                                .map(rol -> new SimpleGrantedAuthority(rol.name()))
                                .collect(Collectors.toList())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }
}