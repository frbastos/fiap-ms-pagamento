package br.com.fiap.pagamento.infra.gateways;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.pagamento.application.usecases.command.NotificacaoPagamentoCommand;
import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import br.com.fiap.pagamento.infra.feignclient.dto.PagamentoPedidoRequest;

class NotificacaoPagamentoGatewayTest {

    @Mock
    private RestTemplate restTemplate;

    private AutoCloseable autoCloseable;

    private NotificacaoPagamentoGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        gateway = new NotificacaoPagamentoGatewayImpl(restTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirEnviarNotificacao() {

        NotificacaoPagamentoCommand comando = new NotificacaoPagamentoCommand(
                "http://localhost:8080",
                BigDecimal.valueOf(15),
                LocalDateTime.now(),
                UUID.randomUUID().toString(),
                StatusPagamento.APROVADO);

        PagamentoPedidoRequest expectedRequest = new PagamentoPedidoRequest(
                comando.transacaoid(),
                comando.statusPagamento(),
                comando.valor());

        when(restTemplate.postForEntity(
                comando.callbackUrl(),
                expectedRequest,
                String.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        gateway.enviarNotificacao(comando);

        verify(restTemplate).postForEntity(
                comando.callbackUrl(),
                expectedRequest,
                String.class);
    }

}
