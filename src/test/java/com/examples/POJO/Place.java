package com.examples.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("place name")
    private String placeName;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("latitude")
    private String latitude;
}
