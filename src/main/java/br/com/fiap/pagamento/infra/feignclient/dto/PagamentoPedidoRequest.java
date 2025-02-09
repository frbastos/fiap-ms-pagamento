package br.com.fiap.pagamento.infra.feignclient.dto;

import java.math.BigDecimal;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;

public record PagamentoPedidoRequest(
    String transacaoId,
    StatusPagamento statusPagamento,
    BigDecimal valor
) {

}
