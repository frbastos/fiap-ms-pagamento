package br.com.fiap.pagamento.infra.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pagamento")
public class PagamentoDocument {

    @Id
    private ObjectId id;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private StatusPagamento status;
   
}
