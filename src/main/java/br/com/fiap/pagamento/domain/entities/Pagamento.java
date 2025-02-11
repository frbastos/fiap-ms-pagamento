package br.com.fiap.pagamento.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pagamento {

    ObjectId id;
    LocalDateTime dataHora;
    BigDecimal valor;
    StatusPagamento statusPagamento;

}
