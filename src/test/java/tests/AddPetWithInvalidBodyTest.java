package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.Pet;
import model.PetCategory;
import model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static constants.AssertionErrorMessages.*;
import static java.lang.String.format;
import static model.PetStatus.VACCINATED;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.*;

public class AddPetWithInvalidBodyTest extends BaseTest {

    // @Autowired
    private Pet petToBeAdded = Pet.builder()
            .id(Integer.parseInt(randomNumeric(6)))
            .category(new PetCategory(1, "Cat"))
            .name("John")
            .photoUrls(new ArrayList<String>() {
                {
                    add("https://www.myowesomecats.com/white");
                    add("https://www.myowesomecats.com/grey");
                }
            })
            .tags(new ArrayList<Tag>() {
                {
                    add(new Tag(1, "tag1"));
                    add(new Tag(2, "tag2"));
                }
            })
            .status(VACCINATED).build();
    // @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addPetWithInvalidBodyTest() throws JsonProcessingException {

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
