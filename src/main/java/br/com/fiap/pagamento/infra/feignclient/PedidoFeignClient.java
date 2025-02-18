package br.com.fiap.pagamento.infra.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.pagamento.infra.feignclient.dto.PagamentoPedidoRequest;

@FeignClient(name = "pedidosClient", url = "${pedidos.service.url}")
public interface PedidoFeignClient {

    @PostMapping("/pedidos/{pedidoId}/pagamento")
    ResponseEntity<Void> webhookPagamento(@PathVariable String pedidoId, @RequestBody PagamentoPedidoRequest request);

}
