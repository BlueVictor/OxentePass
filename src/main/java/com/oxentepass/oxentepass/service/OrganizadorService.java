package com.oxentepass.oxentepass.service;

import com.oxentepass.oxentepass.entity.Organizador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizadorService {
    void cadastrarOrganizador(long usuarioId, Organizador organizador);

    Page<Organizador> listarOrganizadores(Pageable pageable);
}