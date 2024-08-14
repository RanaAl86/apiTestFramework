package com.examples.tests;

import com.examples.utilities.ConfigManager;
import com.examples.utilities.ZipCodeBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ZipCode extends ZipCodeBaseUrl {


    @Test
    public void test1() {

//        String zipCode = "20872";
//        -->  we can also call it from config.properties file
        String zipCode = ConfigManager.getProperty("zipCode"); // -->  we can also call it from config.properties file

        Response response = given().pathParam("zipCode", zipCode)
                .when()
                .get("/us/{zipCode}")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .extract()
                .response();

        response.prettyPrint();

        // Using jsonPath for storing reusable data

        JsonPath jsonPath = response.jsonPath();
        String country = jsonPath.getString("country");
        System.out.println("Country is: " + country);


        // Using response to store reusable data

        String city = response.path("places[0].'place name'");
        System.out.println("City is: " + city);

        // If we had more tha one place will need a List of Map to store each place in 'places'.
        List<Map<String, Object>> allPlaces = jsonPath.getList("places");
        Assert.assertFalse(allPlaces.isEmpty());

        // Storing single object of place in a MAP Object to get the info we need and assert it.
        Map<String, Object> place1 = allPlaces.get(0);
        Assert.assertEquals(place1.get("place name"), "Damascus");
        Assert.assertEquals(place1.get("state"), "Maryland");
        Assert.assertTrue(place1.get("latitude").equals("39.2761"));


    }

    @Test
    public void test2() {

        JsonPath jsonPath = given().contentType(ContentType.JSON)
                .when()
                .get("/us/va/Richmond")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .extract()
                .response().jsonPath();

        jsonPath.prettyPrint();

        List<Map<String, Object>> allPlaces = jsonPath.getList("places");
//        System.out.println(allPlaces);


        Map<String, Object> secondPlace = allPlaces.get(1); // index num starts from 0 inside 'places' in response body
        Assert.assertEquals(secondPlace.get("post code"), "23201");
        Assert.assertTrue(secondPlace.get("place name").equals("Richmond"));

        String state = jsonPath.getString("state");
        String country = jsonPath.getString("country");

        Assert.assertTrue(state.equals("Virginia"));
        Assert.assertEquals(country, "United States");

        System.out.println("Country is: " + country);
        System.out.println("State is: " + state);


    }
}
