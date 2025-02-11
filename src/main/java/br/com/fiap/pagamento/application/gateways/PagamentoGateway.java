package br.com.fiap.pagamento.application.gateways;

import br.com.fiap.pagamento.domain.entities.Pagamento;

public interface PagamentoGateway {

    Pagamento criar(Pagamento pagamento);

}
