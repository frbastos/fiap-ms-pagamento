package br.com.fiap.pagamento.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import br.com.fiap.pagamento.infra.api.dto.QRCodeRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class PagamentoStep {

    private Response response;
    private UUID qrcode;
    private final String ENDPOINT = "http://localhost:8081/pagamentos";

    @When("eu envio uma requisição para gerar um QR Code")
    public void requisicaoParaGerarQRCode() {

        QRCodeRequest request = new QRCodeRequest("Pedido Teste", BigDecimal.valueOf(100), "http://localhost:8080/pedidos/webhook");

        response = given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(ENDPOINT + "/qrcode");
    }

    @Then("o serviço retorna um QR Code válido")
    public void qrCodeValido() {
        response.then().statusCode(HttpStatus.OK.value());

        Map<String, String> responseMap = response.jsonPath().getMap("$");
        qrcode = UUID.fromString(responseMap.get("qrcode"));

        assertNotNull(qrcode);
    }

}
