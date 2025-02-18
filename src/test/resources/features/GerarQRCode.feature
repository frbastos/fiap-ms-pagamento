Feature: Processamento de pagamentos via QR Code

  Scenario: Gerar um QR Code e processar o pagamento
    When eu envio uma requisição para gerar um QR Code
    Then o serviço retorna um QR Code válido
