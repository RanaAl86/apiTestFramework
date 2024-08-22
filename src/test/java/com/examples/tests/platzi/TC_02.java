package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_02 extends PlatziUrl {

    @Test
    public void testPostNewProduct() {

        // Because in the API doc. the images is a List variable of String(s) we created it separately
        List<String> images = new ArrayList<>();
        images.add("https://meowden.com/cdn/shop/products/floral-cat-dress-225.jpg?v=1681147734&width=800");

        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("title", "Cat dress");
        productInfo.put("price", 25);
        productInfo.put("description", "Blue fancy day dress for Cats");
        productInfo.put("categoryId", 1);
        productInfo.put("images", images);

        given().contentType(ContentType.JSON)
                .body(productInfo)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .extract()
                .response().prettyPrint();

    }
}
