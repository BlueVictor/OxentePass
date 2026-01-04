package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.oxentepass.oxentepass.exceptions.EstadoInvalidoException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Pagamento {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private LocalDateTime dataPagamento;

    @OneToOne(mappedBy = "pagamento")
    private Venda venda;

    // Mercado Pago
    private String mercadoPagoId;

    // PIX
    @Column(columnDefinition = "TEXT")
    private String qrCode;

    @Column(columnDefinition = "TEXT")
    private String qrCodeBase64;

    // Métodos de negócio

    // Confirma o pagamento
    public void confirmar() {
        if (this.status != StatusPagamento.PENDENTE) {
            throw new EstadoInvalidoException("Pagamento não pode ser confirmado");
        }
        this.status = StatusPagamento.CONFIRMADO;
        this.dataPagamento = LocalDateTime.now();
    }

    // Cancela o pagamento
    public void cancelar() {
        if (this.status == StatusPagamento.CONFIRMADO) {
            throw new EstadoInvalidoException("Pagamento já confirmado não pode ser cancelado");
        }
        this.status = StatusPagamento.CANCELADO;
    }

    // Estorna o pagamento
    public void estornar() {
        if (this.status != StatusPagamento.CONFIRMADO) {
            throw new EstadoInvalidoException("Apenas pagamentos confirmados podem ser estornados");
        }
        this.status = StatusPagamento.ESTORNADO;

    }

    // Recusa o pagamento
    public void recusar() {
        if (this.status != StatusPagamento.PENDENTE) {
            throw new EstadoInvalidoException("Apenas pagamentos pendentes podem ser recusados");
        }
        status = StatusPagamento.RECUSADO;
    }

    // Define o método de pagamento
    public void setMetodoPagamento(MetodoPagamento metodo) {

        if (this.status != StatusPagamento.PENDENTE) {
            throw new EstadoInvalidoException("Pagamento não está pendente");
        }
    
        if (this.metodo != null) {
            throw new EstadoInvalidoException("Método de pagamento já foi definido");
        }
    
        if (metodo == null) {
            throw new EstadoInvalidoException("Método de pagamento é obrigatório");
        }
    
        this.metodo = metodo;
    }
    
}
