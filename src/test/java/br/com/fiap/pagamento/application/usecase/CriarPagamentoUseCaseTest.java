package br.com.fiap.pagamento.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.application.usecases.CriarPagamentoUseCaseImpl;
import br.com.fiap.pagamento.application.usecases.command.CriaPagamentoCommand;
import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;

class CriarPagamentoUseCaseTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    private AutoCloseable autoCloseable;

    private CriarPagamentoUseCaseImpl usecase;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        usecase = new CriarPagamentoUseCaseImpl(pagamentoGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirCriarPagamento() {
        CriaPagamentoCommand comando = new CriaPagamentoCommand(LocalDateTime.now(),
                BigDecimal.valueOf(15), UUID.randomUUID().toString(), StatusPagamento.APROVADO);

            when(pagamentoGateway.criar(any()))
                .thenAnswer(i -> i.getArgument(0));

            Pagamento pagamento = usecase.criar(comando);

            assertNotNull(pagamento);
            assertEquals(comando.dataHora(), pagamento.getDataHora());
            assertEquals(comando.status(), pagamento.getStatusPagamento());
            assertEquals(comando.valor(), pagamento.getValor());
    }

}
