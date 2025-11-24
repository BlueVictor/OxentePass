package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ingresso {
    @Id
    @GeneratedValue
    private long id;
    private String tipo;
    private BigDecimal valorBase;
    private int quantidadeDisponivel;
    private boolean temMeiaEntrada;
}
