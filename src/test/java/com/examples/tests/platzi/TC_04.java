package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_04 extends PlatziUrl {

    @Test
    public void testDeleteProduct() {

        given().pathParam("id", 145)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response().prettyPrint();
    }
}
