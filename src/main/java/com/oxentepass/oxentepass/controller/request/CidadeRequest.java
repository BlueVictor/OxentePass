package com.oxentepass.oxentepass.controller.request;

import com.oxentepass.oxentepass.entity.Cidade;

import jakarta.validation.constraints.NotBlank;

public record CidadeRequest(
    @NotBlank(message = "O campo \"nome\" é obrigatorio")
    String nome,
    @NotBlank(message = "O campo \"descrição\" é obrigatorio")
    String descricao
) {
    public Cidade paraEntidade() {
        Cidade cidade = new Cidade();

        cidade.setNome(nome);
        cidade.setDescricao(descricao);

        return cidade;
    }
}
