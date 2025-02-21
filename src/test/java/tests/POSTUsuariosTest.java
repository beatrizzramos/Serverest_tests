package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BaseTest;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@DisplayName("Testes da API Rest do módulo de Usuários com POST")

public class POSTUsuariosTest extends BaseTest {

    //private static Faker faker = new Faker(Locale.ENGLISH);

    String endpointUsuario = "/usuarios";

    @Test
    @DisplayName("Criar um novo usuário válido")
    public void testCriarNovoUsuario() {

        // Criando um objeto 'usuario' com dados aleatórios
       // Usuario usuario = new Usuario();
       // usuario.setNome(faker.name().fullName());  // Gerando nome aleatório
       // usuario.setEmail(faker.internet().emailAddress());  // Gerando e-mail aleatório
       // usuario.setPassword("teste");  // Senha fixa (ou pode ser aleatória também)
       // usuario.setAdministrador(true);  // Definindo que o usuário é administrador (pode ser aleatório também)


        //Dados para o body da requisição
        String usuarioParaCadastrar = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biakramos@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        //Enviando a requisiçaõ POST para criar o usuário e validar a resposta

        given()
                .body(usuarioParaCadastrar)
        .when()
                .post(endpointUsuario)
        .then()
                .log().all()
                .statusCode(201)
                .body("message", equalTo(("Cadastro realizado com sucesso")));

    }

    @Test
    @DisplayName("Criar um usuário com email já cadastrado")
    public void testCriarUsuarioComEmailJaCadastrado() {

        String usuarioParaCadastrar = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biziaramoss@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrar)
        .when()
                .post(endpointUsuario)
        .then()
                .statusCode(400)
                .body("message", equalTo(("Este email já está sendo usado")));

    }

    @Test
    @DisplayName("Cadastro com email em formato inválido")
    public void testCriarUsuarioComEmailEmFormatoInvalido() {

        String usuarioParaCadastrarEmailInvalido = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biaramosssqa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarEmailInvalido)
        .when()
                .post(endpointUsuario)
        .then()
                .statusCode(400);
                //.body("message", equalTo("email deve ser um email válido"));
    }

    @Test
    @DisplayName("Cadastro com Servidor Gmail")
    public void testCadastroComServidorGmail(){

        String usuarioParaCadastrarComGmail = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biaramoss@gmail.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComGmail)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(201);

    }
    @Test
    @DisplayName("Cadastro com Servidor Hotmail")
    public void testCadastroComServidorHotmail(){

        String usuarioParaCadastrarComHotmail = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biaramoss@hotmail.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComHotmail)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(201);

    }

    @Test
    @DisplayName("Cadastro com email em Branco")
    public void testCadastroComEmailEmBranco(){

        String usuarioParaCadastrarComEmailEmBranco = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComEmailEmBranco)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Cadastro com Senha com menos de 5 caracteres")
    public void testCadastroComSenhaComMenosDe5Caracteres(){

        String usuarioParaCadastrarComSenhaAbaixode5caracteres = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biakcramoss@yahoo.com.br\",\n" +
                "  \"password\": \"1\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComSenhaAbaixode5caracteres)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(201);


    }

    @Test
    @DisplayName("Cadastro com Senha com mais de 10 caracteres")
    public void testCadastroComSenhaComMaisDe10Caracteres(){

        String usuarioParaCadastrarComSenhaAcimade10caracteres = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"biakcramoss@bol.com.br\",\n" +
                "  \"password\": \"1234567891234\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComSenhaAcimade10caracteres)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(201);


    }

    @Test
    @DisplayName("Cadastro com Senha em Branco")
    public void testCadastroComSenhaEmBranco(){

        String usuarioParaCadastrarComSenhaEmBranco = "{\n" +
                "  \"nome\": \"Beatriz Ramos\",\n" +
                "  \"email\": \"bia@bol.com.br\",\n" +
                "  \"password\": \"\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        given()
                .body(usuarioParaCadastrarComSenhaEmBranco)
                .when()
                .post(endpointUsuario)
                .then()
                .statusCode(400);


    }
}
