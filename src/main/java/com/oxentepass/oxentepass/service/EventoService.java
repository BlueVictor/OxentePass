package com.oxentepass.oxentepass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oxentepass.oxentepass.entity.Avaliacao;
import com.oxentepass.oxentepass.entity.Evento;
import com.oxentepass.oxentepass.entity.Ingresso;
import com.oxentepass.oxentepass.entity.PontoVenda;
import com.oxentepass.oxentepass.entity.Tag;
import com.querydsl.core.types.Predicate;

public interface EventoService {
    public void criarEvento(Evento evento);
    public Page<Evento> listarEventos(Pageable pageable);
    public Page<Evento> listarEventosFiltro (Predicate predicate, Pageable pageable);
    public void editarEvento(Long idEvento, Evento evento); //Talvez faça sentido separar algumas partes da edição (endereço, por exemplo)
    public void deletarEvento(long idEvento);
    // Tags
    public void adicionarTag(long idEvento, long idTag); // Adição de Tag existente
    public void adicionarTag(long idEvento, Tag tag);    // Criação de nova Tag
    public void removerTag(long idEvento, long idTag);
    // Ingressos
    public void adicionarIngresso(long idEvento, Ingresso ingresso);
    public void removerIngresso(long idEvento, long idIngresso);
    // Pontos de venda
    public void adicionarPontoVenda(long idEvento, long idPontoVenda);    // Adição de PontoVenda existente
    public void adicionarPontoVenda(long idEvento, PontoVenda pontoVenda);// Criação de novo PontoVenda 
    public void removerPontoVenda(long idEvento, long idPontoVenda);
    // Avaliações
    public void adicionarAvaliacao(long idEvento, Avaliacao avaliacao);
    public void removerAvaliacao(long idEvento, long idAvaliacao);
}
