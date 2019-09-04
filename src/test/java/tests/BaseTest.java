package tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import spring.AppConfig;

import static io.restassured.RestAssured.given;

@SpringJUnitConfig(AppConfig.class)
abstract class BaseTest {

    @Autowired
    protected RequestSpecification requestSpecification;
    @Value("${success.status.code}")
    protected int successStatusCode;
    @Value("${server.error.status.code}")
    protected int serverErrorStatusCode;
    @Value("${server.error.message}")
    protected String serverErrorMessage;
    @Value("${application.json.contenttype}")
    protected String applicationJsonContentType;
    @Value("${success.max.responsetime}")
    protected int maxResponseTime;

    protected Response addNewPetToTheStore(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .post()
                .then()
                .log().all()
                .extract().response();
    }

    protected Response addNewPetToTheStore(String requestBody) {
        return given(requestSpecification)
                .body(requestBody)
                .post()
                .then()
                .log().all()
                .extract().response();
    }

    protected Response addNewPetToTheStore() {
        return given(requestSpecification)
                .post()
                .then()
                .log().all()
                .extract().response();
    }

}
