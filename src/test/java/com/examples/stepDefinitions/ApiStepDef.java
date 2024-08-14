package com.examples.stepDefinitions;

import com.examples.utilities.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDef {

    private String zipcode;
    private Response response;

    @Given("I have the zipcode {string}")
    public void i_have_the_zipcode(String zip) {

        this.zipcode = zip;
    }

    @When("I send a Get request to the zipcode API")
    public void i_send_a_get_request_to_the_zipcode_api() {

        response = given().baseUri("http://api.zippopotam.us")
                .when().get("/us/" + zipcode);
    }

    @Then("I should receive a response with status {int}")
    public void i_should_receive_a_response_with_status(Integer statusCode) {

        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the response should contain the city {string}")
    public void the_response_should_contain_the_city(String city) {

        String actualCity = response.jsonPath().getString("places[0].'place name'");
        Assert.assertEquals(actualCity, city);
    }

    @Then("the response should contain the state {string}")
    public void the_response_should_contain_the_state(String state) {

        String actualState = response.jsonPath().getString("places[0].state");
        Assert.assertEquals(actualState, state);
    }


}
