package com.examples.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TestsExample {


    // Vilasini Code
    @Test
    public void test_1() {
        Response response = given().get("https://reqres.in/api/users?page=1");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    // Vilasini Code
    @Test
    public void test_2() {
        RestAssured.baseURI = "https://reqres.in/api";
        given().queryParam("page", "2")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

    }

}
