package com.examples.utilities;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class PlatziUrl {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1";
    }
}
