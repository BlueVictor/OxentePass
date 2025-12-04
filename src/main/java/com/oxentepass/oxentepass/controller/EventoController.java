package com.oxentepass.oxentepass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oxentepass.oxentepass.controller.DTOs.EventoRequest;
import com.oxentepass.oxentepass.entity.Evento;
import com.oxentepass.oxentepass.service.EventoService;
import com.querydsl.core.types.Predicate;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    //Operações Básicas
    @PostMapping("/simples")
    public ResponseEntity<String> criarEventoSimples (@RequestBody @Valid EventoRequest dto) {
        eventoService.criarEvento(dto.paraEntidade(true));
        return new ResponseEntity<String>("Evento simples " + dto.nome() + " criado com sucesso!", HttpStatus.CREATED);
    }

    @PostMapping("/composto")
    public ResponseEntity<String> criarEventoComposto (@RequestBody @Valid EventoRequest dto) {
        eventoService.criarEvento(dto.paraEntidade(false));
        return new ResponseEntity<String>("Evento composto " + dto.nome() + " criado com sucesso!", HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<Page<Evento>> listarTodosEventos (Pageable pageable) {
        return new ResponseEntity<Page<Evento>>(eventoService.listarEventos(pageable), HttpStatus.OK);
    }
    
    @GetMapping("/filtro")
    public ResponseEntity<Page<Evento>> listarEventosFiltro (@QuerydslPredicate(root = Evento.class) Predicate predicate, Pageable pageable) {
        return new ResponseEntity<Page<Evento>>(eventoService.listarEventosFiltro(predicate, pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEvento (@PathVariable long id, @RequestBody @Valid EventoRequest dto) {
        eventoService.editarEvento(id, dto.paraEntidade(true));  
        return new ResponseEntity<String>("Evento " + dto.nome() + " editado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEvento (@PathVariable long id) {
        eventoService.deletarEvento(id); 
        return new ResponseEntity<String>("Evento com id " + id + " deletado com sucesso!", HttpStatus.OK);
    }
}
