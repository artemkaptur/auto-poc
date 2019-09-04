package tests;

import io.restassured.response.Response;
import model.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static constants.AssertionErrorMessages.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class AddEmptyPetTest extends BaseTest {

    @Autowired
    private Pet emptyPet;

    @Test
    void addEmptyPetTest() {
        Response addNewPetToTheStoreResponse = addNewPetToTheStore(emptyPet);
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        Pet addedPet = addNewPetToTheStoreResponse.as(Pet.class);
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertAll(
                () -> assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode)),
                () -> assertEquals(responseContentType, applicationJsonContentType,
                        format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType)),
                () -> assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME),
                () -> assertNotNull(addedPet.getId()),
                () -> assertNull(addedPet.getCategory()),
                () -> assertNull(addedPet.getName()),
                () -> assertNull(addedPet.getPhotoUrls()),
                () -> assertNull(addedPet.getTags()),
                () -> assertNull(addedPet.getStatus())
        );
    }

}
