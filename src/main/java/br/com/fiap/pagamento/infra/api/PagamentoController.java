package br.com.fiap.pagamento.infra.api;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.pagamento.application.service.AgendarProcessamentoPagamentoService;
import br.com.fiap.pagamento.application.usecases.command.ProcessaPagamentoCommand;
import br.com.fiap.pagamento.infra.api.dto.QRCodeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final AgendarProcessamentoPagamentoService agendarProcessamentoPagamentoService;

    @PostMapping("/qrcode")
    public ResponseEntity<Map<String, UUID>> gerarQRCode(@Valid @RequestBody QRCodeRequest qrcodeRequest) {
        UUID qrcode = UUID.randomUUID();

        ProcessaPagamentoCommand processaPagamentoCommand = new ProcessaPagamentoCommand(qrcodeRequest.valor(), qrcodeRequest.callbackUrl());
        
        // Agendando processamento de pagamento em 10 segundos
        agendarProcessamentoPagamentoService.agendar(processaPagamentoCommand);

        return ResponseEntity.ok().body(Map.of("qrcode", qrcode));
    }
    
}
