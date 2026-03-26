package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class IngressoVenda {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Ingresso ingresso;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonIgnore
    private Venda venda;

    private int quantidade;
    private boolean meiaEntrada;
    private BigDecimal valorTotal;

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public void setMeiaEntrada(boolean meiaEntrada) {

        this.meiaEntrada = meiaEntrada;
    }

    public void calcularValorTotal() {
        BigDecimal valorUnidade = this.ingresso.getValorBase();

        if (this.meiaEntrada) 
            valorUnidade = valorUnidade.divide(new BigDecimal(2));

        this.valorTotal = valorUnidade.multiply(new BigDecimal(this.quantidade));
    }

}
