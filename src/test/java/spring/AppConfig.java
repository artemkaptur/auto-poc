package spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import model.Pet;
import model.PetCategory;
import model.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.ArrayList;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static model.PetStatus.VACCINATED;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Lazy
@Configuration
@PropertySource(value = {"classpath:expectedResults.properties"}, encoding = "UTF-8")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "petToBeAdded")
    public Pet petToBeAdded() {
        return Pet.builder()
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
    }

    @Bean(name = "emptyPet")
    public Pet emptyPet() {
        return new Pet();
    }

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(name = "requestSpecification")
    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader("api_key", "sa88gg17cb12")
                .log(ALL).build();
    }

}
