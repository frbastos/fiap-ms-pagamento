package br.com.fiap.pagamento.infra.gateways;

import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.infra.gateways.mappers.PagamentoEntityMapper;
import br.com.fiap.pagamento.infra.persistence.PagamentoEntity;
import br.com.fiap.pagamento.infra.persistence.PagamentoRepository;

public class PagamentoRepositoryGateway implements PagamentoGateway {

    private PagamentoRepository pagamentoRepository;

    public PagamentoRepositoryGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento criar(Pagamento payment) {
        PagamentoEntity entity = this.pagamentoRepository.save(PagamentoEntityMapper.toEntity(payment));
        return PagamentoEntityMapper.toPagamento(entity);
    }
}
