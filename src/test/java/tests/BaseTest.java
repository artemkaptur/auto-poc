package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import spring.AppConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@ContextConfiguration(classes = AppConfig.class)
public abstract class BaseTest {

    // @Autowired
    protected RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://petstore.swagger.io")
            .setBasePath("/v2/pet")
            .setContentType(JSON)
            .setAccept(JSON)
            .addHeader("api_key", "sa88gg17cb12")
            .log(ALL).build();
    // @Value("${success.status.code}")
    protected int successStatusCode = 200;
    // @Value("${server.error.status.code}")
    protected int serverErrorStatusCode = 500;
    // @Value("${server.error.message}")
    protected String serverErrorMessage = "something bad happened";
    // @Value("${application.json.contenttype}")
    protected String applicationJsonContentType = "application/json";
    // @Value("${success.max.responsetime}")
    protected int maxResponseTime = 3000;

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
