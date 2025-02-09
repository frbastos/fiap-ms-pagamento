package br.com.fiap.pagamento.infra.gateways;

import org.springframework.web.client.RestTemplate;

import br.com.fiap.pagamento.application.gateways.NotificacaoPagamentoGateway;
import br.com.fiap.pagamento.application.usecases.command.NotificacaoPagamentoCommand;
import br.com.fiap.pagamento.infra.feignclient.dto.PagamentoPedidoRequest;

public class NotificacaoPagamentoGatewayImpl implements NotificacaoPagamentoGateway {

    private RestTemplate restTemplate;

    public NotificacaoPagamentoGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void enviarNotificacao(NotificacaoPagamentoCommand command) {
        PagamentoPedidoRequest pagamentoPedidoRequest = new PagamentoPedidoRequest(command.transacaoid(), command.statusPagamento(), command.valor());
        try {
            restTemplate.postForEntity(command.callbackUrl(), pagamentoPedidoRequest,String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
