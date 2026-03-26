package com.oxentepass.oxentepass.controller.request;

import java.util.List;

import com.oxentepass.oxentepass.entity.IngressoVenda;
import com.oxentepass.oxentepass.entity.Pagamento;
import com.oxentepass.oxentepass.entity.Usuario;
import com.oxentepass.oxentepass.entity.Venda;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VendaRequest(

    @NotNull(message = "O usuário é obrigatório")
    Usuario usuario,
    @NotEmpty(message = "A venda deve ter pelo menos um ingresso")
    List<IngressoVenda> ingressos,
    @NotNull(message = "O pagamento é obrigatório")
    Pagamento pagamento

) {

    public Venda paraEntidade() {

    Venda venda = new Venda();

        venda.setUsuario(usuario);
        venda.setIngressos(ingressos);
        venda.setPagamento(pagamento);
        return venda;

    }

    public List<IngressoVenda> getIngressos() {
        return ingressos;
    }
}
    

