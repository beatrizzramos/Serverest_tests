package services;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    @BeforeAll     //executar antes de todos os testes
    public static void setup() {
        baseURI = "http://localhost";
        port = 3000;
        //basePath //nesta api cada rota tem um path diferente

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
}

