package com.oxentepass.oxentepass.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.oxentepass.oxentepass.exceptions.EstadoInvalidoException;
import com.oxentepass.oxentepass.exceptions.OperacaoProibidaException;
import com.oxentepass.oxentepass.exceptions.RecursoNaoEncontradoException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngressoVenda> ingressos;

    @OneToOne(cascade = CascadeType.ALL)
    private Pagamento pagamento;

    private LocalDateTime dataHoraVenda;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusVenda status;
    // Construtor
    public Venda () {
        this.ingressos = new ArrayList<IngressoVenda>();
        this.status = StatusVenda.ABERTA;
    }

    // Métodos

    // Adiciona um ingresso à venda
    public void addIngresso(IngressoVenda ingressoVenda) {
        ingressoVenda.setVenda(this);
        this.ingressos.add(ingressoVenda);
        calcularValorTotal();
    }

    // Remove um ingresso da venda
    public void removerIngresso(IngressoVenda ingressoVenda) {
        boolean resultado = this.ingressos.remove(ingressoVenda);

        if (!resultado)
            throw new RecursoNaoEncontradoException("Este ingresso não se encontra na venda!");

        calcularValorTotal();
    }

    // Calcula o valor total da venda
    public void calcularValorTotal() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        
        for (var ingressoVenda : ingressos) 
            valorTotal = valorTotal.add(ingressoVenda.getValorTotal());
        
        this.valorTotal = valorTotal;
    }

    // Finaliza a venda
    public void finalizar() {
        if (this.status != StatusVenda.ABERTA) {
            throw new EstadoInvalidoException("A venda só pode ser finalizada se estiver aberta");
        }
    
        this.status = StatusVenda.FINALIZADA;
        this.dataHoraVenda = LocalDateTime.now();
        calcularValorTotal();
    }

    // Cancela a venda
    public void cancelar() {
        if (this.status == StatusVenda.CANCELADA) {
            throw new OperacaoProibidaException("Venda já está cancelada");
        }
    
        devolverIngressos();
        this.status = StatusVenda.CANCELADA;
        this.dataHoraVenda = null;
        this.valorTotal = BigDecimal.ZERO;
    }

    // Marca a venda como paga
    public void marcarComoPaga() {
        if (this.status != StatusVenda.FINALIZADA) {
            throw new EstadoInvalidoException("A venda só pode ser marcada como paga se estiver finalizada");
        }
        status = StatusVenda.PAGA;
    }

    // Devolve os ingressos da venda ao estoque
    private void devolverIngressos() {
        for (IngressoVenda ingressoVenda : ingressos) {
            ingressoVenda.getIngresso().devolverQuantidade(ingressoVenda.getQuantidade());
        }
    }
}
