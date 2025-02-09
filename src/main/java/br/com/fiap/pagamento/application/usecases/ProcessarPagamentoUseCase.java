package br.com.fiap.pagamento.application.usecases;

import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;

public interface ProcessarPagamentoUseCase {

    void processar(ProcessaPagamentoCommand processaPagamentoMockCommand);

}
