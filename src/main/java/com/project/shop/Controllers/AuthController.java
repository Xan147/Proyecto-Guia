package com.project.shop.Controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.Dtos.LoginRequestDto;
import com.project.shop.Models.Usuario;
import com.project.shop.Repositories.UsuarioRepository;
import com.project.shop.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(usuario);

        //Construir la respuesta con el token y la información del usuario
        Map<String, Object> respuesta = Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 200,
                        "mensaje", "Login exitoso",
                        "usuario", usuario.getNombre(),
                        "roles", usuario.getRoles(),
                        "token", jwtToken);

        return ResponseEntity.ok(respuesta);
    }
}