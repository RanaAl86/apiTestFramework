package com.examples.tests;

import com.examples.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CreateAirLine {
//    api.instantwebtools.net/v1/airlines

    @Test
    public void createAirline() {
        String endpoint = "https://api.instantwebtools.net/v1/airlines";
        Map<String, Object> payload = Payload.getCreateAirlinePayloadFromString_MAP("125879645223abc", "ABC Airlines", "USA", "ABC", "ABS SLO", "USA", "www.abcAirlines.com", "2020");

        Response response = RestUtils.performPost(endpoint, payload, new HashMap<>());
        Assert.assertEquals(response.statusCode(), 200);
    }


}
