package com.imgur;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

public class AccountTests extends BaseTest{
    static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void setUp() {
        headers.put("Authorization", "Bearer " + token);
    }

    @Test
    void getAccountInfoTest() {
        given()
                .headers(headers)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .statusCode(200);
    }


}
