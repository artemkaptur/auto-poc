package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetCategory {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

}
