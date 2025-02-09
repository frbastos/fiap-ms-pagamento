package br.com.fiap.pagamento.application.gateways;

import br.com.fiap.pagamento.application.usecases.command.NotificacaoPagamentoCommand;

public interface NotificacaoPagamentoGateway {

    void enviarNotificacao(NotificacaoPagamentoCommand notificacaoPagamentoCommand);

}
