CREATE TABLE IF NOT EXISTS pagamento (
    id BIGSERIAL,
    data_hora TIMESTAMP NOT NULL,
    valor DECIMAL(19,2) NOT NULL,
    transacao_id VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);