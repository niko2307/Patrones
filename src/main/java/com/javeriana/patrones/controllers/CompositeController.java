package com.javeriana.patrones.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.patrones.Dtos.QuejaDTO;
import com.javeriana.patrones.composite.CompositeQuejaService;
import com.javeriana.patrones.composite.GrupoQueja;

@RestController
@RequestMapping("/api/composite")
public class CompositeController {
    private final CompositeQuejaService compositeService;

    public CompositeController(CompositeQuejaService compositeService) {
        this.compositeService = compositeService;
    }

    @GetMapping("/por-empresa")
    public ResponseEntity<List<QuejaDTO>> obtenerAgrupadasPorEmpresa() {
        GrupoQueja root = compositeService.construirPorEmpresa();
        return ResponseEntity.ok(root.obtenerQuejas());
    }
}
