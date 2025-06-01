
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

import com.javeriana.patrones.Dtos.EmpresaDTO;
import com.javeriana.patrones.services.EmpresaVigiladaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaVigiladaController {
    private final EmpresaVigiladaService empresaService;

    @PostMapping("/registrar")
    public ResponseEntity<EmpresaDTO> registrar(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nueva = empresaService.registrarEmpresa(empresaDTO);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obtenerPorId(@PathVariable Long id) {
        EmpresaDTO dto = empresaService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listar() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }
}
