package br.com.fiap.pagamento.application.usecases.command;

import java.math.BigDecimal;

public record EnvioPagamentoCommand(
    Long pedidoId,
    BigDecimal valor
) {

}
