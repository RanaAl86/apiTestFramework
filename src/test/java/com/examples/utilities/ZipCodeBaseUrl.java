package com.examples.utilities;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class ZipCodeBaseUrl {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://api.zippopotam.us";
    }

}
