
package com.javeriana.patrones.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javeriana.patrones.Dtos.UsuarioDTO;
import com.javeriana.patrones.model.Usuario;
import com.javeriana.patrones.repository.UsuarioRepository;
import com.javeriana.patrones.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
     private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}

