package com.oxentepass.oxentepass.controller.advice;

import java.util.Map;

import lombok.Data;

@Data
public class ErroResponse {
    private int status;
    private String erro;
    private String mensagem;
    private String path;
    private Map<String, String> detalhes;
}
