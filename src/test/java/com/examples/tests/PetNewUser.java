package com.examples.tests;

import com.examples.utilities.ExcelDataReader;
import com.examples.utilities.ExcelDataReader_MultiCells;
import com.examples.utilities.PetStoreUrl;
import com.examples.utilities.WordDataReader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetNewUser extends PetStoreUrl {

    @Test
    public void testPostReq() {

        // Read data from Word doc.
        Map<String, Object> userData = WordDataReader.readDataFromWord("petUser.docx");
        System.out.println(userData);

        given().contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }


    @Test
    public void testUser() {

        // Read one user from Excel
        Map<String, String> userdata = ExcelDataReader.readDataFromExcel("OneUser.xlsx");
        System.out.println(userdata);

        given().contentType(ContentType.JSON)
                .body(userdata)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }

    @Test
    public void testMultiUsers() {

        // Read data from Excel sheet
        List<Map<String, String>> userData = ExcelDataReader_MultiCells.readDataFromExcel("PetUser.xlsx", 3);
        System.out.println(userData); // just for me to see if data is correct

        given().contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();
    }
}
