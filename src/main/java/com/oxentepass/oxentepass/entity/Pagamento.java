package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pagamento {
    @Id
    @GeneratedValue
    private long id;
    private BigDecimal valor;

    // Certamente, mais atributos serão necessários.
}
