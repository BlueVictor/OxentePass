package com.oxentepass.oxentepass.controller.request;

import java.math.BigDecimal;

import com.oxentepass.oxentepass.entity.Ingresso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IngressoRequest(

    @NotBlank(message = "O campo \"tipoIngresso\" é obrigatório.")
    String tipoIngresso,

    @NotNull(message = "O campo \"valorBase\" é obrigatório.")
    @Min(value = 0, message = "O valor base do ingresso deve ser positivo.")
    BigDecimal valorBase,

    @NotNull(message = "O campo \"quantidadeDisponivel\" é obrigatório.")
    @Min(value = 1, message = "A quantidade deve ser, no mínimo, uma unidade.")
    int quantidadeDisponivel,

    @NotNull(message = "O campo \"temMeiaEntrada\" é obrigatório.")
    boolean temMeiaEntrada

) {
    public Ingresso paraEntidade() {
        Ingresso ingresso = new Ingresso();

        ingresso.setTipo(tipoIngresso);
        ingresso.setValorBase(valorBase);
        ingresso.setQuantidadeDisponivel(quantidadeDisponivel);
        ingresso.setTemMeiaEntrada(temMeiaEntrada);
        
        return ingresso;
    }
}
    

