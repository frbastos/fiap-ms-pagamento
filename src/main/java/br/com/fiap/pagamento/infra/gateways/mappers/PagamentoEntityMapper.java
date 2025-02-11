package br.com.fiap.pagamento.infra.gateways.mappers;

import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.infra.persistence.PagamentoDocument;

public class PagamentoEntityMapper {

    public static Pagamento toPagamento(PagamentoDocument entity) {
        return new Pagamento(entity.getId(), entity.getDataHora(), entity.getValor(),
                entity.getStatus());
    }

    public static PagamentoDocument toDocument(Pagamento pagamento) {
        return new PagamentoDocument(pagamento.getId(), pagamento.getDataHora(), pagamento.getValor(),
                pagamento.getStatusPagamento());
    }

}
