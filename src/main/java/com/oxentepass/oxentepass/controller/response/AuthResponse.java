package com.oxentepass.oxentepass.controller.response;

public record AuthResponse(
    long id,
    String nome,
    String email,
    String tipoUsuario,
    boolean autenticado
) {
    public static AuthResponse paraDTO(long id, String nome, String email, boolean organizador) {
        return new AuthResponse(
            id,
            nome,
            email,
            organizador ? "ORGANIZADOR" : "USUARIO",
            true
        );
    }
}
