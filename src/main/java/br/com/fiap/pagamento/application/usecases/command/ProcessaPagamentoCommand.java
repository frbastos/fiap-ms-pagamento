package br.com.fiap.pagamento.application.usecases.command;

import java.math.BigDecimal;

public record ProcessaPagamentoCommand(
    BigDecimal valor,
    String callbackUrl
) {

}
