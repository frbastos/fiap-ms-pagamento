package br.com.fiap.pagamento.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.pagamento.application.gateways.NotificacaoPagamentoGateway;
import br.com.fiap.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pagamento.application.service.AgendarProcessamentoPagamentoService;
import br.com.fiap.pagamento.application.usecases.CriarPagamentoUseCase;
import br.com.fiap.pagamento.application.usecases.CriarPagamentoUseCaseImpl;
import br.com.fiap.pagamento.application.usecases.ProcessarPagamentoUseCase;
import br.com.fiap.pagamento.application.usecases.ProcessarPagamentoUseCaseImpl;
import br.com.fiap.pagamento.infra.api.scheduler.ProcessamentoScheduler;
import br.com.fiap.pagamento.infra.gateways.NotificacaoPagamentoGatewayImpl;

@Configuration
public class BeanConfigurationPayment {

    @Bean
    CriarPagamentoUseCase createPaymentUseCase(PagamentoGateway paymentRepository) {
        return new CriarPagamentoUseCaseImpl(paymentRepository);
    }

    @Bean
    NotificacaoPagamentoGateway notificacaoPagamentoGateway(RestTemplate restTemplate) {
        return new NotificacaoPagamentoGatewayImpl(restTemplate);
    }

    @Bean
    ProcessarPagamentoUseCase processarPagamentoUseCase(PagamentoGateway pagamentoGateway,
            NotificacaoPagamentoGateway notificacaoPagamentoGateway,
            CriarPagamentoUseCase criarPagamentoUseCase) {
        return new ProcessarPagamentoUseCaseImpl(pagamentoGateway, notificacaoPagamentoGateway, criarPagamentoUseCase);
    }

    @Bean
    AgendarProcessamentoPagamentoService agendarProcessamentoPagamentoService(
            ProcessarPagamentoUseCase processarPagamentoUseCase) {
        return new ProcessamentoScheduler(processarPagamentoUseCase);
    }

}