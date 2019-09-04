package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static constants.AssertionErrorMessages.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class AddPetWithoutBodyTest extends BaseTest {

    @Test
    void addPetWithoutBodyTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore();
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertAll(
                () -> assertEquals(statusCode, serverErrorStatusCode, format(STATUS_CODE_IS_NOT_ERRORED, statusCode)),
                () -> assertEquals(responseContentType, applicationJsonContentType,
                        format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType)),
                () -> assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME),
                () -> assertEquals(responseMessage, serverErrorMessage)
        );
    }

}
