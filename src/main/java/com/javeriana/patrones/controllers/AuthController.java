
package com.javeriana.patrones.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.patrones.Dtos.AuthRequest;
import com.javeriana.patrones.Dtos.AuthResponse;
import com.javeriana.patrones.Dtos.UsuarioDTO;
import com.javeriana.patrones.Dtos.UsuarioRegistroDTO;
import com.javeriana.patrones.model.Usuario;
import com.javeriana.patrones.repository.UsuarioRepository;
import com.javeriana.patrones.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

 @PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

    Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    String token = jwtUtil.generateToken(userDetails, usuario.getId());

    return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getRol().name()));
}


    @PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody UsuarioRegistroDTO dto) {
    if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    Usuario usuario = modelMapper.map(dto, Usuario.class);
    usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
    Usuario registrado = usuarioRepository.save(usuario);

    UserDetails userDetails = userDetailsService.loadUserByUsername(registrado.getEmail());
    String token = jwtUtil.generateToken(userDetails, registrado.getId());

    return ResponseEntity.ok(new AuthResponse(
        token,
        registrado.getId(),
        registrado.getRol().name()
    ));
}

}
