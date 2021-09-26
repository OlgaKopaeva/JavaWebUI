package com.imgur;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.is;

public class AccountTests extends BaseTest {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setAuth(oauth2(token))
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    @Test
    @DisplayName("Get information about account")
    void testGetAccountInformation() {
        given()
                .spec(requestSpecification)
                .log().all()
                .response()
                .spec(responseSpecification)
                .log().all()
                .body("data.id", is(Integer.parseInt(userId)))
                .when()
                .get("https://api.imgur.com/3/account/{username}", username);
    }

    @Test
    @DisplayName("Get account settings")
    void testGetAccountSettings() {
        given()
                .spec(requestSpecification)
                .log().all()
                .response()
                .spec(responseSpecification)
                .log().all()
                .body("data.account_url", is(username))
                .when()
                .get("https://api.imgur.com/3/account/me/settings");
    }
}
