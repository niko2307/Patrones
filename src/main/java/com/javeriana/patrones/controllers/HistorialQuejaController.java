
package com.javeriana.patrones.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.patrones.Dtos.HistorialQuejaDTO;
import com.javeriana.patrones.services.HistorialQuejaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialQuejaController {
    private final HistorialQuejaService historialService;

    @PostMapping("/registrar")
    public ResponseEntity<HistorialQuejaDTO> registrar(@RequestBody HistorialQuejaDTO dto) {
        HistorialQuejaDTO nuevo = historialService.registrarHistorial(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/queja/{quejaId}")
    public ResponseEntity<List<HistorialQuejaDTO>> listarPorQueja(@PathVariable Long quejaId) {
        return ResponseEntity.ok(historialService.listarPorQueja(quejaId));
    }
}
