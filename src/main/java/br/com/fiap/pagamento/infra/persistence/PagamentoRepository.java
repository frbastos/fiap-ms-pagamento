package br.com.fiap.pagamento.infra.persistence;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoDocument, UUID> {
}
