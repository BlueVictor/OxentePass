package com.oxentepass.oxentepass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oxentepass.oxentepass.controller.response.AuthResponse;
import com.oxentepass.oxentepass.entity.Usuario;
import com.oxentepass.oxentepass.exceptions.NaoAutenticadoException;
import com.oxentepass.oxentepass.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String SESSION_USER_ID = "usuarioId";

    @Autowired
    private UsuarioService usuarioService;

    // Método auxiliar
    private Usuario obterUsuarioDaSessao(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SESSION_USER_ID) == null) {
            throw new NaoAutenticadoException("Nenhum usuario autenticado na sessao atual.");
        }

        long usuarioId = (long) session.getAttribute(SESSION_USER_ID);

        return usuarioService.buscarUsuarioPorId(usuarioId);
    }

    // Operações
    @Operation(summary = "Usuario autenticado", description = "Retorna os dados basicos do usuario autenticado na sessao atual")
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> buscarUsuarioAutenticado(HttpServletRequest request) {
        Usuario usuario = obterUsuarioDaSessao(request);

        return new ResponseEntity<AuthResponse>(AuthResponse.paraDTO(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Logout", description = "Encerra a sessao autenticada atual")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
