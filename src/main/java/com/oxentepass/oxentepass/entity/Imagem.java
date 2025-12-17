package com.oxentepass.oxentepass.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Imagem { // Apenas armazena metadados da imagem
    @Id
    @GeneratedValue
    private long id;
    private String chaveS3;     // Armazenamento no AWS S3
    private String nome;        // Nome do arquivo
    private String tipoArquivo; // Extensão do arquivo (png, jpeg, ...)
    private boolean ehCapa;     // Se é a imagem de capa do evento
}
