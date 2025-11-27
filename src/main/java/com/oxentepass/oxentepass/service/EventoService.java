package com.oxentepass.oxentepass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oxentepass.oxentepass.entity.Avaliacao;
import com.oxentepass.oxentepass.entity.Evento;
import com.oxentepass.oxentepass.entity.Ingresso;
import com.oxentepass.oxentepass.entity.PontoVenda;
import com.querydsl.core.types.Predicate;

public interface EventoService {
    public void criarEvento(Evento evento);
    public Page<Evento> listarEventos(Pageable pageable);
    public Page<Evento> listarEventosFiltro (Predicate predicate, Pageable pageable);
    public void editarEvento(Long id, Evento evento); //Talvez faça sentido separar algumas partes da edição (endereço, por exemplo)
    public void deletarEvento(long id);
    // Tags
    public void adicionarTag(long idEvento, long idTag); //Sobrecarga talvez faça sentido (add tag existente ou criar nova tag)
    public void removerTag(long idEvento, long idTag);
    // Ingressos
    public void adicionarIngresso(long id, Ingresso Ingresso);
    public void removerIngresso(long id, Ingresso Ingresso);
    // Pontos de venda
    public void adicionarPontoVenda(long id, PontoVenda pontoVenda);
    public void removerPontoVenda(long id, PontoVenda pontoVenda);
    // Avaliações
    public void adicionarAvaliacao(long id, Avaliacao avaliacao);
    public void removerAvaliacao(long id, Avaliacao avaliacao);
}
