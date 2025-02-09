package br.com.fiap.pagamento.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pagamento {

    Long id;
    LocalDateTime dataHora;
    BigDecimal valor;
    String transacaoId;
    StatusPagamento statusPagamento;

}
