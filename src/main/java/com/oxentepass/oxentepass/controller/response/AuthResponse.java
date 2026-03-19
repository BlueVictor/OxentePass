package com.oxentepass.oxentepass.controller.response;

import com.oxentepass.oxentepass.entity.Organizador;
import com.oxentepass.oxentepass.entity.Usuario;

public record AuthResponse(
    long id,
    String nome,
    String email,
    String tipoUsuario,
    boolean autenticado
) {
    public static AuthResponse paraDTO(Usuario usuario) {
        return new AuthResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario instanceof Organizador ? "ORGANIZADOR" : "USUARIO",
            true
        );
    }
}
