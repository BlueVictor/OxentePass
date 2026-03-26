package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxentepass.oxentepass.exceptions.EstadoInvalidoException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    private BigDecimal valorBase;
    private int quantidadeDisponivel;
    private boolean temMeiaEntrada;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    @JsonIgnore
    private Evento evento;

    // Métodos

    // Reduz a quantidade disponível de ingressos

    public Long getEventoId() {
        if (this.evento != null) {
            return this.evento.getId();
        }
        return null;
    }

    // Reduz a quantidade disponível de ingressos
    
    public void reduzirQuantidade(int quantidade) {
        if (quantidade > quantidadeDisponivel) 
            throw new EstadoInvalidoException("Quantidade de ingressos inválida.");

        this.quantidadeDisponivel -= quantidade;
    }

    // Devolve a quantidade de ingressos
    public void devolverQuantidade(int quantidade) {
        this.quantidadeDisponivel += quantidade;
    }

}
