package by.a1qa.klimov.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    String street;
    String suite;
    String city;
    String zipcode;
    @JsonProperty("geo")
    Geo geo;
}
