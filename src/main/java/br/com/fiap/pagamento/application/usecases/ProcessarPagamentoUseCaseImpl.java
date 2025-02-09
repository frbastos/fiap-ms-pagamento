package br.com.fiap.pagamento.application.usecases;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;

import br.com.fiap.pagamento.application.gateways.NotificacaoPagamentoGateway;
import br.com.fiap.pagamento.application.usecases.command.CriaPagamentoCommand;
import br.com.fiap.pagamento.application.usecases.command.NotificacaoPagamentoCommand;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;
import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;

public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private final NotificacaoPagamentoGateway processadorPagamentoGateway;
    private final CriarPagamentoUseCase criarPagamentoUseCase;

    public ProcessarPagamentoUseCaseImpl(
            NotificacaoPagamentoGateway processadorPagamentoGateway,
            CriarPagamentoUseCase criarPagamentoUseCase) {
        this.processadorPagamentoGateway = processadorPagamentoGateway;
        this.criarPagamentoUseCase = criarPagamentoUseCase;
    }

    @Async
    @Override
    public void processar(ProcessaPagamentoCommand command) {

        // Criar pagamento
        CriaPagamentoCommand criaPagamentoCommand = new CriaPagamentoCommand(
                LocalDateTime.now(), command.valor(), UUID.randomUUID().toString(), StatusPagamento.APROVADO);
        Pagamento pagamentoCriado = criarPagamentoUseCase.criar(criaPagamentoCommand);

        // Notificar pagamento
        NotificacaoPagamentoCommand notificacaoPagamentoCommand = new NotificacaoPagamentoCommand(
                command.callbackUrl(),
                pagamentoCriado.getValor(),
                pagamentoCriado.getDataHora(),
                pagamentoCriado.getTransacaoId(),
                pagamentoCriado.getStatusPagamento());

        processadorPagamentoGateway.enviarNotificacao(notificacaoPagamentoCommand);
    }

}
