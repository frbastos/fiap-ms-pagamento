package br.com.fiap.pagamento.application.service;

import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;

public interface AgendarProcessamentoPagamentoService {

    public void agendar(ProcessaPagamentoCommand command);

}
