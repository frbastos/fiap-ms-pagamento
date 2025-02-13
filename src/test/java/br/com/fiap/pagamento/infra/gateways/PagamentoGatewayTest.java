package br.com.fiap.pagamento.infra.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.pagamento.domain.entities.Pagamento;
import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import br.com.fiap.pagamento.infra.persistence.PagamentoRepository;

class PagamentoGatewayTest {

    @Mock
    private PagamentoRepository pagamentoRepository;;

    private AutoCloseable autoCloseable;
    private PagamentoGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        gateway = new PagamentoGatewayImpl(pagamentoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirCriarPagamento() {

        Pagamento pagamento = new Pagamento(new ObjectId(), LocalDateTime.now(), BigDecimal.valueOf(15),
                StatusPagamento.APROVADO);

        when(pagamentoRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        Pagamento resultado = gateway.criar(pagamento);

        assertNotNull(resultado);
        assertEquals(pagamento.getId(), resultado.getId());
        assertEquals(pagamento.getDataHora(), resultado.getDataHora());
        assertEquals(pagamento.getStatusPagamento(), resultado.getStatusPagamento());
        assertEquals(pagamento.getValor(), resultado.getValor());

    }

}
