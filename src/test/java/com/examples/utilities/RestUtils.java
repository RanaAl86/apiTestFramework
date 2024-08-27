package com.examples.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    public static Response performPost(String endpoint, String requestPayload, Map<String, Object> headers) {
        return RestAssured.given().log().all()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post()
                .then().log().all().extract().response();
    }

    public static Response performPost(String endpoint, Map<String, Object> requestPayload, Map<String, Object> headers) {
        return RestAssured.given().log().all()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post()
                .then().log().all().extract().response();
    }
}
