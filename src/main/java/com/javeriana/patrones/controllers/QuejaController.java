
package com.javeriana.patrones.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.patrones.Dtos.QuejaDTO;
import com.javeriana.patrones.services.QuejaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quejas")
@RequiredArgsConstructor
public class QuejaController {
    private final QuejaService quejaService;

    @PostMapping("/registrar")
    public ResponseEntity<QuejaDTO> registrar(@RequestBody QuejaDTO quejaDTO) {
        QuejaDTO nueva = quejaService.registrarQueja(quejaDTO);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<QuejaDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(quejaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<QuejaDTO>> listarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(quejaService.listarPorEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuejaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(quejaService.obtenerPorId(id));
    }
}
