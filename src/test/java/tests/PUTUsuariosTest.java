package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes da API Rest do módulo de Usuários com PUT")

public class PUTUsuariosTest extends BaseTest {

    String endpointEditarUsuarioSucesso = "/usuarios/Sq5GqmEkwZRiNEW6";
    String endpointEditarUsuarioIDInexistente = "/usuarios/1234567";
   // String endpointEditarUsuarioEmail = "/usuarios"; // Não precisa porque basta reutilizar o mesmo usuário da edição com sucesso

    @Test
    @DisplayName("Editar um usuário já cadastrado")
    public void testEditarUsuarioComSucesso() {

        String usuarioParaEditar = "{\n" +
                "  \"nome\": \"Beatriz Katriny Chaves Vieira\",\n" +
                "  \"email\": \"biakramos@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaEditar)
        .when()
                .put(endpointEditarUsuarioSucesso)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

    }

    @Test
    @DisplayName("Editar um usuário com ID inexistente")
    public void testEditarcomIDInexistente() {

        String usuarioParaEditar = "{\n" +
                "  \"nome\": \"Beatriz Katriny Chaves Vieira\",\n" +
                "  \"email\": \"biakccramos@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaEditar)

        .when()
                .put(endpointEditarUsuarioIDInexistente)
        .then()
                .log().all()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }

    @Test
    @DisplayName("Editar um usuário com Email em uso")
    public void testEditarComEmailEmUso(){

        String usuarioParaEditar = "{\n" +
                "  \"nome\": \"Beatriz Katriny Chaves Vieira\",\n" +
                "  \"email\": \"biakccramos@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaEditar)
        .when()
                .put(endpointEditarUsuarioSucesso)
        .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Este email já está sendo usado"));


    }


}



