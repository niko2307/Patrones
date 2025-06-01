
package com.javeriana.patrones.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.patrones.Dtos.UsuarioDTO;
import com.javeriana.patrones.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;


    @GetMapping("/buscar")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarPorEmail(email)
                .map(usuario -> new ResponseEntity<>(new UsuarioDTO(
                    usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol().name()
                ), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }
}
