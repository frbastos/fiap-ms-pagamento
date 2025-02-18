package br.com.fiap.pagamento.application.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.pagamento.application.gateways.NotificacaoPagamentoGateway;
import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.application.usecases.CriarPagamentoUseCase;
import br.com.fiap.pagamento.application.usecases.ProcessarPagamentoUseCaseImpl;
import br.com.fiap.pagamento.application.usecases.command.CriaPagamentoCommand;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;
import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;

class ProcessarPagamentoUseCaseTest {

    private AutoCloseable autoCloseable;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private NotificacaoPagamentoGateway processadorPagamentoGateway;

    @Mock
    private CriarPagamentoUseCase criarPagamentoUseCase;

    private ProcessarPagamentoUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new ProcessarPagamentoUseCaseImpl(pagamentoGateway, processadorPagamentoGateway,
                criarPagamentoUseCase);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirProcessarPagamento() {

        ProcessaPagamentoCommand comando = new ProcessaPagamentoCommand(BigDecimal.valueOf(15),
                "http://localhost:8080");

        Pagamento pagamento = new Pagamento(new ObjectId(), LocalDateTime.now(), comando.valor(),
                StatusPagamento.APROVADO);

        when(criarPagamentoUseCase.criar(any(CriaPagamentoCommand.class)))
                .thenReturn(pagamento);

        doNothing().when(processadorPagamentoGateway).enviarNotificacao(any());

        usecase.processar(comando);

        // Esperar um pouco para garantir que o @Async executou
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500); // Pequeno delay para garantir execução assíncrona
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).join();

        verify(criarPagamentoUseCase).criar(any(CriaPagamentoCommand.class));
        verify(pagamentoGateway).criar(pagamento);
        verify(processadorPagamentoGateway)
                .enviarNotificacao(argThat(notificacao -> notificacao.callbackUrl().equals(comando.callbackUrl()) &&
                        notificacao.valor().equals(comando.valor())));
    }

}
