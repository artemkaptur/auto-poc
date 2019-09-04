package tests;

import io.restassured.response.Response;
import model.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static constants.AssertionErrorMessages.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class AddNewPetToTheStoreTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;

    @Test
    void addNewPetToTheStoreTest() {
        Response addNewPetToTheStoreResponse = addNewPetToTheStore(petToBeAdded);
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertAll(
                () -> assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode)),
                () -> assertEquals(responseContentType, applicationJsonContentType,
                        format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType)),
                () -> assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME),
                () -> assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), WRONG_PET_WAS_CREATED)
        );
    }

}
