package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes da API Rest do módulo de Usuários")

public class POSTUsuariosTest extends BaseTest {

    String endpointUsuario = "/usuarios";

    @Test
    @DisplayName("Criar um novo usuário válido")
    public void testCriarNovoUsuario() {

        //Dados para o body da requisição
        String usuarioParaCadastrar = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biziaramoss@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        //Enviando a requisiçaõ POST para criar o usuário e validar a resposta

        given()
                .body(usuarioParaCadastrar)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(201)
                .body("message", equalTo(("Cadastro realizado com sucesso")));

    }
}
