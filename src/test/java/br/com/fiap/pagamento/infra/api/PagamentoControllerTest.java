package br.com.fiap.pagamento.infra.api;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.pagamento.application.service.AgendarProcessamentoPagamentoService;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;
import br.com.fiap.pagamento.infra.api.dto.QRCodeRequest;

class PagamentoControllerTest {

    private MockMvc mockMvc;

    private AutoCloseable autoCloseable;

    @Mock
    private AgendarProcessamentoPagamentoService agendarProcessamentoPagamentoService;

    private PagamentoController pagaemntoController;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        pagaemntoController = new PagamentoController(
                agendarProcessamentoPagamentoService);
        mockMvc = MockMvcBuilders.standaloneSetup(pagaemntoController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void devePermitirGerarQRCode() throws Exception {

        QRCodeRequest request = new QRCodeRequest("Pedido 1", BigDecimal.valueOf(15), "http://localhost:8080");
        ProcessaPagamentoCommand processaPagamentoCommand = new ProcessaPagamentoCommand(request.valor(),
                request.callbackUrl());

        doNothing().when(agendarProcessamentoPagamentoService).agendar(processaPagamentoCommand);

        mockMvc.perform(
                post("/pagamentos/qrcode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qrcode").exists());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
