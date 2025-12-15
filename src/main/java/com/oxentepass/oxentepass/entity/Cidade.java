package com.oxentepass.oxentepass.entity;

import java.util.List;

import com.oxentepass.oxentepass.exceptions.EstadoInvalidoException;
import com.oxentepass.oxentepass.exceptions.RecursoNaoEncontradoException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Cidade {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String descricao;
    
    @ManyToMany
    private List<Tag> tags;

    //Métodos
    public void addTag(Tag tag) {
        if (this.tags.contains(tag))
            throw new EstadoInvalidoException("A tag informada já consta na cidade " + this.nome + ".");

        this.tags.add(tag);
    }

    public void removerTag(Tag tag) {
        if (!this.tags.remove(tag)) 
            throw new RecursoNaoEncontradoException("A tag informada não consta na cidade " + this.nome + ".");
    }
}
