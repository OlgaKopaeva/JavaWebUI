package com.imgur;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AccountTests extends BaseTest {

    @Test
    @DisplayName("Get information about account")
    void testGetAccountInformation() {
        given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .auth().oauth2(token)
                .log().all()
                .expect()
                .log().all()
                .statusCode(200)
                .body("data.id", is(Integer. parseInt(userId)))
                .when()
                .get("https://api.imgur.com/3/account/{username}", username);

    }

}
