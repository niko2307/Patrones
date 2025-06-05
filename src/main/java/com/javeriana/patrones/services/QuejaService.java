
package com.javeriana.patrones.services;

import java.util.List;

import com.javeriana.patrones.Dtos.QuejaDTO;

public interface  QuejaService {
    QuejaDTO registrarQueja(QuejaDTO quejaDTO);
    List<QuejaDTO> listarPorUsuario(Long usuarioId);
    List<QuejaDTO> listarPorEstado(String estado);
    QuejaDTO obtenerPorId(Long id);
    List<QuejaDTO> listarTodas();
}

