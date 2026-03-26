package com.oxentepass.oxentepass.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.oxentepass.oxentepass.entity.Pagamento;
import com.oxentepass.oxentepass.entity.StatusVenda;
import com.oxentepass.oxentepass.entity.Venda;

public record VendaResponse(
    long id,
    UsuarioResponse usuario,
    List<IngressoVendaResponse> ingressos,
    BigDecimal valorTotal,
    Pagamento pagamento,
    LocalDateTime dataHoraVenda,
    StatusVenda status
) {
    public static VendaResponse paraDTO(Venda venda) {
        return new VendaResponse(
            venda.getId(),
            UsuarioResponse.paraDTO(venda.getUsuario()),
            venda.getIngressos().stream()
                 .map(IngressoVendaResponse::paraDTO)
                 .toList(),
            venda.getValorTotal(),
            venda.getPagamento(),
            venda.getDataHoraVenda(),
            venda.getStatus()
        );
    }
}