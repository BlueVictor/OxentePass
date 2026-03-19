package com.oxentepass.oxentepass.controller.response;

import com.oxentepass.oxentepass.entity.Organizador;

public record OrganizadorResponse(
    String cnpj,
    String telefone,
    String biografia
) {
    public static OrganizadorResponse paraDTO(Organizador organizador) {
        return new OrganizadorResponse(
            organizador.getCnpj(),
            organizador.getTelefone(),
            organizador.getBiografia()
        );
    }
}
