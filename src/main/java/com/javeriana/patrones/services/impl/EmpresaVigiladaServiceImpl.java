
package com.javeriana.patrones.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javeriana.patrones.Dtos.EmpresaDTO;
import com.javeriana.patrones.model.EmpresaVigilada;
import com.javeriana.patrones.observer.EventoNotificacion;
import com.javeriana.patrones.observer.GestorNotificaciones;
import com.javeriana.patrones.repository.EmpresaVigiladaRepository;
import com.javeriana.patrones.services.EmpresaVigiladaService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class EmpresaVigiladaServiceImpl implements EmpresaVigiladaService {
    private final EmpresaVigiladaRepository repository;
    private final ModelMapper modelMapper;
    private final GestorNotificaciones gestorNotificaciones;

    public EmpresaVigiladaServiceImpl(
        EmpresaVigiladaRepository repository,
        ModelMapper modelMapper,
        GestorNotificaciones gestorNotificaciones
    ) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.gestorNotificaciones = gestorNotificaciones;
    }
    @Override
    public EmpresaDTO registrarEmpresa(EmpresaDTO dto) {
        EmpresaVigilada empresa = modelMapper.map(dto, EmpresaVigilada.class);
        EmpresaVigilada guardada = repository.save(empresa);

        // Notificación simulada
        EventoNotificacion evento = new EventoNotificacion(
            "🏢 Nueva empresa vigilada registrada: " + guardada.getNombre(),
            "empresa@correo.com",
            "REGISTRO_EMPRESA"
        );
        gestorNotificaciones.notificarObservadores(evento);

        return modelMapper.map(guardada, EmpresaDTO.class);
    }

    @Override
    public EmpresaDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));
    }

    @Override
    public List<EmpresaDTO> listarEmpresas() {
        return repository.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }
}
