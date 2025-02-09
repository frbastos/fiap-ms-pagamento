package br.com.fiap.pagamento.infra.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record PagamentoAprovadoRequest(
    @NotNull Long pedidoId,
    BigDecimal valor
) {

}
