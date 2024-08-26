package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_04 extends PlatziUrl {

    @Test
    public void testDeleteProduct() {

        Response response = given().pathParam("id", 90)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        Assert.assertEquals(responseBody.trim(),"true");

    }
}
