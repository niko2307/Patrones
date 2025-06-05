package com.javeriana.patrones.composite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javeriana.patrones.Dtos.EmpresaDTO;
import com.javeriana.patrones.Dtos.QuejaDTO;
import com.javeriana.patrones.model.Queja;
import com.javeriana.patrones.repository.QuejaRepository;

@Service
public class CompositeQuejaService {
    private final QuejaRepository quejaRepository;
    private final ModelMapper modelMapper;

    public CompositeQuejaService(QuejaRepository quejaRepository, ModelMapper modelMapper) {
        this.quejaRepository = quejaRepository;
        this.modelMapper = modelMapper;
    }

    public GrupoQueja construirPorEmpresa() {
        List<QuejaDTO> todas = quejaRepository.findAll().stream().map(q -> {
            QuejaDTO dto = modelMapper.map(q, QuejaDTO.class);
            dto.setEmpresa(modelMapper.map(q.getEmpresaVigilada(), EmpresaDTO.class)); // ✅ mapea empresa
            return dto;
        }).collect(Collectors.toList());

        Map<Long, GrupoQueja> agrupadas = new HashMap<>();

        for (QuejaDTO q : todas) {
            Long idEmpresa = q.getEmpresa().getId(); // ✅ ahora usamos el nombre real
            String nombre = q.getEmpresa().getNombre();
            GrupoQueja grupo = agrupadas.computeIfAbsent(idEmpresa, id -> new GrupoQueja(nombre));
            grupo.agregar(new QuejaHoja(q));
        }

        GrupoQueja raiz = new GrupoQueja("Todas las quejas agrupadas por empresa");
        agrupadas.values().forEach(raiz::agregar);
        return raiz;
    }
}
