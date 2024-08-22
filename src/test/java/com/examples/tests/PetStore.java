package com.examples.tests;

import com.examples.utilities.ConfigManager;
import com.github.javafaker.Faker;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetStore {

    // Set the base URI for the API
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Test()
    public void createUser() {
        // Create JSON payload
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", "Lili123");
        userInfo.put("firstName", "Rana");
        userInfo.put("lastName", "AlDa");
        userInfo.put("email", "lili123@lili.com");
        userInfo.put("password", "aaa123");
        userInfo.put("phone", "0123456789");


        //Send POST request and get response
        Response response = given().contentType(ContentType.JSON)
                .body(userInfo)
                .when()
                .post("/user") // POST request is to create/add new data to API
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.prettyPrint();
//        System.out.println("Create user Response body: " + response.prettyPrint());
    }


    @Test()
    public void logIn() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("username", "Lili123")
                .queryParam("password", "aaa123")
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Login Response body:  " + response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");

//        System.out.println("Message: " + message);

    }


    @Test()
    public void deleteUser() {
        Response response = given()
                .pathParam("username", "Lili123")
                .when()
                .delete("/user/{username}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Delete user Response Body: " + response.prettyPrint());
    }

    // Creating a user using Java-Faker
    @Test
    public void createUser_JavaFaker() {

        Faker faker = new Faker();

        String username = faker.name().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().cellPhone();
//        String artist = faker.artist().name();  ---> This is just an example of how you add the data from JavaFaker
//        System.out.println("artist = " + artist);


        System.out.println("JF username: " + username);
        System.out.println("JF firstName: " + firstName);
        System.out.println("JF lastName: " + lastName);
        System.out.println("JF email: " + email);
        System.out.println("JF phone number: " + phone);


        // create a map for the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("phone", phone);

        System.out.println("<---------------------------------->");

        given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user")
                .prettyPeek() // will print the request and response details
                .then()
                .statusCode(200);
    }
}
