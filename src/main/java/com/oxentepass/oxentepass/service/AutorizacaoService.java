package com.oxentepass.oxentepass.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AutorizacaoService {

    public long obterIdUsuarioAutenticado(HttpServletRequest request);
    public void exigirMesmoUsuario(HttpServletRequest request, long usuarioId);
    public void exigirUsuarioOrganizador(HttpServletRequest request);
    
}
