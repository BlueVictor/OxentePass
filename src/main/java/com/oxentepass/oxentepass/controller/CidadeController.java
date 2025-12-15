package com.oxentepass.oxentepass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oxentepass.oxentepass.controller.request.CidadeRequest;
import com.oxentepass.oxentepass.controller.request.TagRequest;
import com.oxentepass.oxentepass.entity.Cidade;
import com.oxentepass.oxentepass.service.CidadeService;
import com.querydsl.core.types.Predicate;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    // Operações Básicas
    @PostMapping
    public ResponseEntity<String> criarCidade (@RequestBody @Valid CidadeRequest dto) {
        cidadeService.criarCidade(dto.paraEntidade());
        return new ResponseEntity<String>(
            "Cidade " + dto.nome() + " criada com sucesso!", 
            HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Page<Cidade>> listarCidades (Pageable pageable) {
        return new ResponseEntity<Page<Cidade>>(
            cidadeService.listarCidades(pageable),
            HttpStatus.OK
        );
    }
    
    @GetMapping("/filtro")
    public ResponseEntity<Page<Cidade>> listarCidadesFiltro (Predicate predicate, Pageable pageable) {
        return new ResponseEntity<Page<Cidade>>(
            cidadeService.listarCidadesFiltro(predicate, pageable),
            HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarCidade (@PathVariable long id, @RequestBody @Valid CidadeRequest dto) {
        cidadeService.editarCidade(id, dto.paraEntidade());
        
        return new ResponseEntity<String>(
            "Cidade " + dto.nome() + " editada com sucesso!", 
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCidade (@PathVariable long id) {
        cidadeService.deletarCidade(id);

        return new ResponseEntity<String>(
            "Cidade com id " + id + " deletada com sucesso!", 
            HttpStatus.OK
        );
    }

    // Tags
    @PatchMapping("/{idCidade}/addTag/{idTag}")
    public ResponseEntity<String> adicionarTagExistente (@PathVariable long idCidade, @PathVariable long idTag) {
        cidadeService.adicionarTagExistente(idCidade, idTag);

        return new ResponseEntity<String>(
            "Tag com id " + idTag + " adicionada a cidade com id " + idCidade + " com sucesso!", 
            HttpStatus.OK
        );
    }

    @PatchMapping("/{idCidade}/addTag")
    public ResponseEntity<String> adicionarTagNova (@PathVariable long idCidade, @RequestBody @Valid TagRequest dto) {
        cidadeService.adicionarTagNova(idCidade, dto.paraEntidade());

        return new ResponseEntity<String>(
            "Tag " + dto.tag() + " adicionada a cidade com id " + idCidade + " com sucesso!", 
            HttpStatus.OK
        );
    }

    @PatchMapping("/{idCidade}/removerTag/{idTag}")
    public ResponseEntity<String> removerTag (@PathVariable long idCidade, @PathVariable long idTag) {
        cidadeService.removerTag(idCidade, idTag);

        return new ResponseEntity<String>(
            "Tag com id " + idTag + " removida de cidade com id " + idCidade + " com sucesso!", 
            HttpStatus.OK
        );
    }
}
