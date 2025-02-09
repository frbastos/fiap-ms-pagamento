package br.com.fiap.pagamento.infra.gateways.mappers;

import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.infra.persistence.PagamentoEntity;

public class PagamentoEntityMapper {

    public static Pagamento toPagamento(PagamentoEntity entity) {
        return new Pagamento(entity.getId(), entity.getDataHora(), entity.getValor(), entity.getTransacaoId(),
                entity.getStatus());
    }

    public static PagamentoEntity toEntity(Pagamento pagamento) {
        return new PagamentoEntity(pagamento.getId(), pagamento.getDataHora(), pagamento.getValor(),
                pagamento.getTransacaoId(), pagamento.getStatusPagamento());
    }

}
