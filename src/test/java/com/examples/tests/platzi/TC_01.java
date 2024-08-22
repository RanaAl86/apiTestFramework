package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_01 extends PlatziUrl {

    @Test
    public void testGetAllProducts() {

        given().contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .response().prettyPrint();

    }
}
