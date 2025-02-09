package br.com.fiap.pagamento.application.usecases;

import br.com.fiap.pagamento.application.usecases.command.CriaPagamentoCommand;
import br.com.fiap.pagamento.domain.entities.Pagamento;

public interface CriarPagamentoUseCase {

    Pagamento criar(CriaPagamentoCommand criaPagamentoCommand);

}
