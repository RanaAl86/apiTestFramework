package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_03 extends PlatziUrl {

    @Test
    public void testUpdateProductInfo() {

        Map<String, Object> updatedInfo = new HashMap<>();
        updatedInfo.put("price", 45);
        updatedInfo.put("description", "Blue Flower Print day dress for Cats");

        given().contentType(ContentType.JSON)
                .pathParam("id", 145)
                .body(updatedInfo)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}
