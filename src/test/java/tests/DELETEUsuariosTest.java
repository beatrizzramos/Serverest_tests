package tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes da API Rest do módulo de Usuários com DELETE")

public class DELETEUsuariosTest extends BaseTest {

    String endpointDeletarUsuario = "/usuarios/Sq5GqmEkwZRiNEW6";

    @Test
    @DisplayName("Excluir usuário que não possui carrinho")
    public void testExcluirUsuarioSemCarrinho(){

        when()
                .delete(endpointDeletarUsuario)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }




}
