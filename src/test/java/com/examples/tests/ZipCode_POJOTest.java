package com.examples.tests;

//import com.examples.POJO.Place;

import com.examples.POJO.Place;
import com.examples.POJO.ZipCode_POJO;
import com.examples.utilities.ZipCodeBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ZipCode_POJOTest extends ZipCodeBaseUrl {

    @Test
    public void getReq_Richmond() {
        Response response = given().when()
                .get("/us/va/Richmond")
                .then()
                .statusCode(200)
                .extract()
                .response();

        ZipCode_POJO zipCodeInfo = response.as(ZipCode_POJO.class);

//        System.out.println("zipCodeInfo = " + zipCodeInfo);
//
//        System.out.println("zipCodeInfo.getCountry() = " + zipCodeInfo.getCountry());
//        System.out.println("zipCodeInfo.getState() = " + zipCodeInfo.getState());

    }

}
