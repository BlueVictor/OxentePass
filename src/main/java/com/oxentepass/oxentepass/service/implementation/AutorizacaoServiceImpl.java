package com.oxentepass.oxentepass.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oxentepass.oxentepass.entity.Usuario;
import com.oxentepass.oxentepass.exceptions.OperacaoProibidaException;
import com.oxentepass.oxentepass.service.AuthSessionService;
import com.oxentepass.oxentepass.service.AutorizacaoService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    @Autowired
    private AuthSessionService authSessionService;

    @Override
    public long obterIdUsuarioAutenticado(HttpServletRequest request) {
        Usuario usuario = authSessionService.obterUsuarioAutenticado(request);

        return usuario.getId();
    }

    @Override
    public void exigirMesmoUsuario(HttpServletRequest request, long usuarioId) {
        long usuarioAutenticadoId = obterIdUsuarioAutenticado(request);

        if (usuarioAutenticadoId != usuarioId) {
            throw new OperacaoProibidaException("O usuário autenticado não tem permissão para acessar este recurso.");
        }
    }

    @Override
    public void exigirUsuarioOrganizador(HttpServletRequest request) {
        if (!authSessionService.usuarioAutenticadoEhOrganizador(request)) {
            throw new OperacaoProibidaException("A operação exige um organizador autenticado.");
        }
    }
}
