package com.javeriana.patrones.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javeriana.patrones.Dtos.QuejaDTO;
import com.javeriana.patrones.adapter.EmpresaExternaAdapter;
import com.javeriana.patrones.adapter.ProveedorEmpresaExterno;
import com.javeriana.patrones.model.EmpresaVigilada;
import com.javeriana.patrones.model.EstadoQueja;
import com.javeriana.patrones.model.Queja;
import com.javeriana.patrones.model.Usuario;
import com.javeriana.patrones.observer.EventoNotificacion;
import com.javeriana.patrones.observer.GestorNotificaciones;
import com.javeriana.patrones.observer.ObservadorConsola;
import com.javeriana.patrones.observer.ObservadorCorreo;
import com.javeriana.patrones.repository.EmpresaVigiladaRepository;
import com.javeriana.patrones.repository.QuejaRepository;
import com.javeriana.patrones.repository.UsuarioRepository;
import com.javeriana.patrones.services.QuejaService;
import com.javeriana.patrones.strategy.StrategyContext;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuejaServiceImpl implements QuejaService {
    private final QuejaRepository repository;
    private final ModelMapper modelMapper;
    private final EmpresaVigiladaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final GestorNotificaciones gestorNotificaciones; 
    private final StrategyContext strategyContext;

    public QuejaServiceImpl(
        QuejaRepository repository,
        EmpresaVigiladaRepository empresaRepository,
        UsuarioRepository usuarioRepository,
        ModelMapper modelMapper,
        GestorNotificaciones gestorNotificaciones, 
        StrategyContext strategyContext
    ) {
        this.repository = repository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.gestorNotificaciones = gestorNotificaciones;
        this.strategyContext = strategyContext;
        this.gestorNotificaciones.agregarObservador(new ObservadorConsola());
        this.gestorNotificaciones.agregarObservador(new ObservadorCorreo());
    }

    @Override
public QuejaDTO registrarQueja(QuejaDTO dto) {
    // ✅ Usar Adapter para validar si la empresa está activa
    ProveedorEmpresaExterno proveedor = new EmpresaExternaAdapter();
    if (!proveedor.empresaEstaActiva(dto.getEmpresaId())) {
        throw new IllegalArgumentException("La empresa no está activa actualmente en el sistema externo.");
    }

    // ✅ Crear instancia de Queja
    Queja queja = new Queja();
    queja.setDescripcion(dto.getDescripcion());
    queja.setEstado(EstadoQueja.valueOf(dto.getEstado().toUpperCase()));
    queja.setFechaRegistro(LocalDateTime.now());

    // ✅ Asociar Empresa Vigilada
    EmpresaVigilada empresa = empresaRepository.findById(dto.getEmpresaId())
        .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));
    queja.setEmpresaVigilada(empresa);

    // ✅ Asociar Usuario
    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    queja.setUsuario(usuario);

    // ✅ Aplicar lógica de enrutamiento con STRATEGY (modifica vencimiento y ruta asignada)
    strategyContext.enrutar(queja);

    // ✅ Guardar queja con lógica de Strategy ya aplicada
    Queja guardada = repository.save(queja);

    // ✅ Notificar con Observer
    EventoNotificacion evento = new EventoNotificacion(
        "\uD83D\uDCE2 Se ha registrado una nueva queja contra su empresa '" + empresa.getNombre() + "' por el usuario " + usuario.getNombre(),
        "empresa@" + empresa.getNombre().toLowerCase().replace(" ", "") + ".com",
        "NUEVA_QUEJA"
    );
    gestorNotificaciones.notificarObservadores(evento);

    // ✅ Devolver DTO
    return modelMapper.map(guardada, QuejaDTO.class);
}

    @Override
    public List<QuejaDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream()
                .map(queja -> modelMapper.map(queja, QuejaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuejaDTO> listarPorEstado(String estado) {
        EstadoQueja estadoQueja = EstadoQueja.valueOf(estado.toUpperCase());
        return repository.findByEstado(estadoQueja).stream()
                .map(queja -> modelMapper.map(queja, QuejaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuejaDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(q -> modelMapper.map(q, QuejaDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Queja no encontrada"));
    }

    @Override
public List<QuejaDTO> listarTodas() {
    return repository.findAll().stream()
            .map(q -> modelMapper.map(q, QuejaDTO.class))
            .collect(Collectors.toList());
}

}
