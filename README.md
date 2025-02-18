
# FIAP - Arquitetura de Software -> Sistema de Pedido

## Descrição do Projeto

Este projeto é um sistema de pedidos desenvolvido em **Java Spring Boot**, permitindo que clientes façam pedidos personalizados, realizem pagamentos via QRCode do **Mercado Pago**, acompanhem o status de seus pedidos e recebam notificações quando o pedido estiver pronto. O sistema também oferece gerenciamento de clientes, produtos e categorias, além de um painel administrativo para monitoramento de pedidos.

## Funcionalidades

### Pedido
- **Identificação do Cliente**: Clientes podem se identificar via CPF, nome e e-mail, ou optar por fazer pedidos anonimamente.
- **Montagem de Combo**: Clientes podem montar seus combos escolhendo:
    - Lanche
    - Acompanhamento
    - Bebida
    - Sobremesa
- Exibição de nome, descrição e preço dos produtos em cada etapa.

### Pagamento
- **QRCode Mercado Pago**: Pagamento integrado via QRCode.

### Acompanhamento
- **Status do Pedido**: Monitoramento em tempo real dos estados do pedido:
    - Recebido
    - Em preparação
    - Pronto
    - Finalizado

### Entrega
- **Notificação**: Notificação ao cliente quando o pedido estiver pronto para retirada.

### Gerenciamento
- **Clientes**: Gestão de clientes para campanhas promocionais.
- **Produtos e Categorias**: Gerenciamento de produtos com nome, categoria, preço, descrição e imagens.
- **Pedidos**: Acompanhamento de pedidos em andamento e tempo de espera.

## Microserviço de Pagamento

O Microserviço de Pagamento é responsável pelo processamento dos pagamentos dos pedidos, garantindo que todas as transações sejam verificadas antes da preparação do pedido. Ele um gera um QR Code e envia notificações de pagamento via webhook para o microserviço de pedidos.

**Fluxo de Comunicação**
1. O Microserviço de Pedidos solicita um QR Code para pagamento.
2. O Microserviço de Pagamento simula um processamento de 10s.
3. Após 10 segundos o O Microserviço de Pagament envia uma notificação via webhook informando o status da transação.
4. O Microserviço de Pagamento atualiza o status do pagamento e informa o Microserviço de Pedidos.
5. O pedido só é enviado para a cozinha após a confirmação do pagamento.

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/frbastos/fiap-ms-pagamento
   cd fiap-ms-pagamento
   ```

2. Executar testes unitário:
    ```
        mvn test -Punit-test
    ```

3. Executar testes de sistema (BDD):
    ```
        mvn test -Psystem-test
    ```

4. Executar projeto com spring boot:
    ```
        mvn spring-boot:run
    ```

## Documentação Complementar

- [Notion do Projeto](https://global-gorilla-13f.notion.site/FIAP-Projeto-Lanchonete-26bfdcca5de84ce8974cbfad8286dcc2)
- [Miro com Fluxos](https://miro.com/app/board/uXjVK3DvRAo=/?share_link_id=212036327976)
- [Collection Postman](https://drive.google.com/file/d/1XwWG5h5oT1e2Nfxoc222UkvCIpkwJ_pI/view?usp=sharing)

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).