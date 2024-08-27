package com.examples.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

public class MelanieTest {

    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;

    @Given("the API for adding product is {string}")
    public void the_api_for_adding_product_is(String url) {
        RestAssured.baseURI = "https://fakestoreapi.com";
        givenPart = RestAssured.given().contentType(ContentType.JSON);
    }


    @When("I send a POST request with the following product details {string}, {string},{string},{string},{string}")
    public void i_send_a_post_request_with_the_following_product_details(String title, String price, String description, String image, String category) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("price", price);
        requestBody.put("description", description);
        requestBody.put("image", image);
        requestBody.put("category", category);

        response = givenPart.body(requestBody).log().all().when().post("/products");
        thenPart = response.then();

    }


    @Then("the response status code is {string}")
    public void the_response_status_code_is(String expectedStatusCode) {
        int statusCode = Integer.parseInt(expectedStatusCode);
        thenPart.statusCode(statusCode);
    }

    @Then("the response body should contain product details")
    public void the_response_body_should_contain_product_details() {
        thenPart.body("title", Matchers.notNullValue())
                .body("price", Matchers.notNullValue())
                .body("description", Matchers.notNullValue())
                .body("image", Matchers.notNullValue())
                .body("category", Matchers.notNullValue());
    }

    @Then("the response should include an {string} field")
    public void the_response_should_include_an_field(String id) {
        thenPart.body(id, Matchers.notNullValue());

        String productId = response.jsonPath().getString("id");
        System.out.println("productId = " + productId);
    }

}
