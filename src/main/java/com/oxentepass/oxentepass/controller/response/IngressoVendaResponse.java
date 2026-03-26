package com.oxentepass.oxentepass.controller.response;

import java.math.BigDecimal;

import com.oxentepass.oxentepass.entity.IngressoVenda;

public record IngressoVendaResponse(
    long id,
    int quantidade,
    BigDecimal valorTotal
) {
    public static IngressoVendaResponse paraDTO(IngressoVenda item) {
        return new IngressoVendaResponse(
            item.getId(),
            item.getQuantidade(),
            item.getValorTotal()
        );
    }
}
