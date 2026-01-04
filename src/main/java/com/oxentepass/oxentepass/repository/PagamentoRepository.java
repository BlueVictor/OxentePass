package com.oxentepass.oxentepass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.oxentepass.oxentepass.entity.Pagamento;
import com.oxentepass.oxentepass.entity.QPagamento;
import com.oxentepass.oxentepass.entity.StatusPagamento;
import com.querydsl.core.types.dsl.NumberPath;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, QuerydslPredicateExecutor<Pagamento>, QuerydslBinderCustomizer<QPagamento> {

    // Método para buscar pagamento por ID do MercadoPago
    Optional<Pagamento> findByMercadoPagoId(String mercadoPagoId);

    // Método para buscar pagamentos por status
    List<Pagamento> findByStatus(StatusPagamento status);

    @Override
    default void customize(QuerydslBindings bindings, QPagamento root) {

        // Bind para id
        bindings.bind(root.id).first((NumberPath<Long> path, Long value) -> path.eq(value));

        // Bind para metodo
        bindings.bind(root.metodo).first((path, value) -> path.eq(value));

        // Bind para status
        bindings.bind(root.status).first((path, value) -> path.eq(value));

        // Bind para ValorIgual  
        bindings.bind(root.valor).as("ValorIgual").first((path, value) -> path.eq(value));

        // Bind para ValorMenor
        bindings.bind(root.valor).as("ValorMenor").first((path, value) -> path.loe(value));

        // Bind para ValorMaior
        bindings.bind(root.valor).as("ValorMaior").first((path, value) -> path.goe(value));

        // Binds para dataPagamento inicio
        bindings.bind(root.dataPagamento).as("dataInicio").first((path, value) -> path.goe(value));

        // Bind para dataPagamento fim
        bindings.bind(root.dataPagamento).as("dataFim").first((path, value) -> path.loe(value));

    }

}