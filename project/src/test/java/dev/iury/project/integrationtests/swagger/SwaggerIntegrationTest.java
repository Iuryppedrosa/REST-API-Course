package dev.iury.project.integrationtests.swagger;

import dev.iury.project.configs.TestConfigs;
import dev.iury.project.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content =
                given()
                        .basePath("/swagger-ui/index.html")
                        .port(8080)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();
        assertTrue(content.contains("Swagger UI"));
    }
}
