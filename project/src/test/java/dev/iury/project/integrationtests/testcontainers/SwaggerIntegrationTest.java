package dev.iury.project.integrationtests.testcontainers;

import dev.iury.project.configs.TestConfigs;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content =
                given()
                        .basePath("/swagger-ui/index.html")
                        .port(TestConfigs.SERVER_PORT)
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
