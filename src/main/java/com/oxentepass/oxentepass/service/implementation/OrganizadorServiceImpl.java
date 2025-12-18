package com.oxentepass.oxentepass.service.implementation;

import com.oxentepass.oxentepass.entity.Organizador;
import com.oxentepass.oxentepass.entity.Usuario;
import com.oxentepass.oxentepass.exceptions.EstadoInvalidoException;
import com.oxentepass.oxentepass.exceptions.RecursoNaoEncontradoException;
import com.oxentepass.oxentepass.repository.OrganizadorRepository;
import com.oxentepass.oxentepass.repository.UsuarioRepository;
import com.oxentepass.oxentepass.service.OrganizadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizadorServiceImpl implements OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void cadastrarOrganizador(long usuarioId, Organizador organizador) {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new RecursoNaoEncontradoException("Usuário com id " + usuarioId + " não encontrado"));

        if (organizadorRepository.existsById(usuarioId)) {
            throw new EstadoInvalidoException("Este usuário já é um organizador");
        }

        String telefoneLimpo = organizador.getTelefone().replaceAll("\\D", "");
        String cnpjLimpo = organizador.getCnpj().replaceAll("\\D", "");

        organizador.setUsuario(usuario);
        organizador.setCnpj(cnpjLimpo);
        organizador.setTelefone(telefoneLimpo);

        organizadorRepository.save(organizador);
    }

    @Override
    public Page<Organizador> listarOrganizadores(Pageable pageable) {
        return organizadorRepository.findAll(pageable);
    }
}