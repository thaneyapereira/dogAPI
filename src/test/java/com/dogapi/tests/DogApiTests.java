package com.dogapi.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DogApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dog.ceo/api";
    }

    @Test
    public void testGetAllBreeds() {
        given()
        .when()
            .get("/breeds/list/all")
        .then()
            .statusCode(200)
            .body("message.terrier", notNullValue());
    }

    @Test
    public void testGetImagesForTerrier() {
        given()
        .when()
            .get("/breed/terrier/images")
        .then()
            .statusCode(200)
            .body("message", not(empty()))
            .body("message.size()", greaterThan(0));
    }

    @Test
    public void testGetRandomImageForTerrier() {
        Response response = given()
        .when()
            .get("/breed/terrier/images/random")
        .then()
            .statusCode(200)
            .body("message", containsString("terrier"))
            .extract().response();
        
        System.out.println("Random Terrier Image URL: " + response.jsonPath().getString("message"));
    }
}