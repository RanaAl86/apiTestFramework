package com.examples.tests.platzi;

import com.examples.utilities.PlatziUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class All_in_One extends PlatziUrl {

    @Test
    public void test() {

        System.out.println("----------Get ALL----------");

        given().contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .response();
//                .prettyPrint();

        System.out.println("----------Add New Product----------");

        // Because in the API doc. the images is a List variable of String(s) we created it separately
        List<String> images = new ArrayList<>();
        images.add("https://meowden.com/cdn/shop/products/floral-cat-dress-225.jpg?v=1681147734&width=800");

        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("title", "Cat dress");
        productInfo.put("price", 25);
        productInfo.put("description", "Day dress for Cats");
        productInfo.put("categoryId", 1);
        productInfo.put("images", images);

        JsonPath jsonPath = given().contentType(ContentType.JSON)
                .body(productInfo)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath();

        jsonPath.prettyPrint();

        Object productId = jsonPath.get("id"); // we will use in all other tests

        System.out.println("----------Update a Product----------");

        Map<String, Object> updatedInfo = new HashMap<>();
        updatedInfo.put("price", 45);
        updatedInfo.put("description", "Blue Flower Print day dress for Cats");

        given().contentType(ContentType.JSON)
                .pathParam("id", productId)
                .body(updatedInfo)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        System.out.println("----------Delete a Product----------");

        given().pathParam("id", productId)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response().prettyPrint();

        System.out.println("----------Confirming a Product was deleted----------");

        given().pathParam("id", productId)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(400)
                .extract().response().prettyPrint();
    }
}
