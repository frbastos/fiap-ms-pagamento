package br.com.fiap.pagamento.application.scheduler;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.pagamento.application.usecases.ProcessarPagamentoUseCase;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;
import br.com.fiap.pagamento.infra.api.scheduler.ProcessamentoScheduler;

class ProcessamentoSchedulerTest {

    private AutoCloseable autoCloseable;

    @Mock
    private ScheduledExecutorService scheduler;

    @Mock
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    private ProcessamentoScheduler usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new ProcessamentoScheduler(processarPagamentoUseCase);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirAgendaPagamento() {
        ProcessaPagamentoCommand command = new ProcessaPagamentoCommand(BigDecimal.valueOf(15),
                "http://loclahost:8080");

        usecase.agendar(command);

        await().atMost(12, TimeUnit.SECONDS)
               .untilAsserted(() -> verify(processarPagamentoUseCase, times(1)).processar(command));
    }

}
