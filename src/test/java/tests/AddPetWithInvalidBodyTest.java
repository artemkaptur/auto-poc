package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static constants.AssertionErrorMessages.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class AddPetWithInvalidBodyTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addPetWithInvalidBodyTest() throws JsonProcessingException {
        int randomNumberForSpoilRequestBody = 13;
        Response addNewPetToTheStoreResponse = addNewPetToTheStore(objectMapper.writeValueAsString(petToBeAdded)
                .substring(randomNumberForSpoilRequestBody));
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
