package br.com.fiap.pagamento.infra.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QRCodeRequest(
        @NotBlank(message = "Descrição deve ser preenchida") String descricao,
        @NotNull @DecimalMin(value = "0.1", message = "Valor não pode ser inferior a R$ 0,1") BigDecimal valor,
        @NotBlank(message = "Url callback deve ser preenchida") String callbackUrl) {

}
