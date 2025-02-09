package br.com.fiap.pagamento.infra.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.pagamento.domain.objectvalues.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    
    @NotNull
    @JoinColumn(name = "transacao_id")
    private String transacaoId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPagamento status;
   
}
