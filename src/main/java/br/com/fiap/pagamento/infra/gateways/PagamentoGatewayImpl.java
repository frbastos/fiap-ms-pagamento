package br.com.fiap.pagamento.infra.gateways;

import org.springframework.stereotype.Component;

import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.infra.gateways.mappers.PagamentoEntityMapper;
import br.com.fiap.pagamento.infra.persistence.PagamentoDocument;
import br.com.fiap.pagamento.infra.persistence.PagamentoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository pagamentoRepository;

    @Override
    public Pagamento criar(Pagamento pagamento) {
        PagamentoDocument entity = PagamentoEntityMapper.toDocument(pagamento);
        entity = pagamentoRepository.save(entity);
        return PagamentoEntityMapper.toPagamento(entity);
    }

}
