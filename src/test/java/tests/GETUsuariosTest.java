package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes da API Rest do módulo de Usuários com GET")

public class GETUsuariosTest extends BaseTest {

    String endpointUsuario = "/usuarios";
    String endpointUsuarioComID = "/usuarios/5Rogk77qwbyguDb1";
    String endpointUsuarioComIDInvalido = "/usuarios/123456789";

    @Test
    @DisplayName("Listar Usuários Cadastrados")
    public void testListarUsuariosCadastrados(){

        when()
                .get(endpointUsuario)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("Listar Usuário pelo ID")
    public void testListarUsuarioPorID(){

        when()
                .get(endpointUsuarioComID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("Listar Usuário com ID inexistente")
    public void testListarUsuariocomIdInexistente(){

        when()
                .get(endpointUsuarioComIDInvalido)
                .then()
                .log().all()
                .statusCode(400);
    }

}
