package com.examples.utilities;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class PetStoreUrl {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

}
