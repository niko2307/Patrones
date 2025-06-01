
package com.javeriana.patrones.services;

import java.util.Optional;

import com.javeriana.patrones.Dtos.UsuarioDTO;
import com.javeriana.patrones.model.Usuario;

public interface  UsuarioService {
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    Optional<Usuario> buscarPorEmail(String email);
}
