package br.com.fiap.pagamento.infra.api.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.fiap.pagamento.application.service.AgendarProcessamentoPagamentoService;
import br.com.fiap.pagamento.application.usecases.ProcessarPagamentoUseCase;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;

public class ProcessamentoScheduler implements AgendarProcessamentoPagamentoService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    public ProcessamentoScheduler(ProcessarPagamentoUseCase processarPagamentoUseCase) {
        this.processarPagamentoUseCase = processarPagamentoUseCase;
    }

    @Override
    public void agendar(ProcessaPagamentoCommand command) {
        scheduler.schedule(() -> processarPagamentoUseCase.processar(command), 10, TimeUnit.SECONDS);
    }

}
