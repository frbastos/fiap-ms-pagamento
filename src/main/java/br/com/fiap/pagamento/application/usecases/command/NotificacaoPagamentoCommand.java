package br.com.fiap.pagamento.application.usecases.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;

public record NotificacaoPagamentoCommand(
    String callbackUrl,
    BigDecimal valor,
    LocalDateTime dataHora,
    String transacaoid,
    StatusPagamento statusPagamento
) {

}
