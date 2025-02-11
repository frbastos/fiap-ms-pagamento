package br.com.fiap.pagamento.application.usecases;

import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.application.usecases.command.CriaPagamentoCommand;
import br.com.fiap.pagamento.domain.entities.Pagamento;

public class CriarPagamentoUseCaseImpl implements CriarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public CriarPagamentoUseCaseImpl(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento criar(CriaPagamentoCommand command){
        Pagamento payment = new Pagamento(null, command.dataHora(), command.valor(), command.status());
        return pagamentoGateway.criar(payment);
    }
}
