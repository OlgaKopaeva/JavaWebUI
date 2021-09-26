package com.imgur;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.auth.AUTH;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTests extends BaseTest {
    static String galleryImageHash = "Fp9I0i5";
    static int commentId;
    static String text = "Today is a nice day";
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFormParam("image_id", galleryImageHash)
            .setAuth(oauth2(token))
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("success", is(true))
            .build();

    @Order(1)
    @Test
    @DisplayName("Post comment")
    void testPostComment() throws InterruptedException {
        commentId = given()
                .log().all()
                .spec(requestSpecification)
                .formParam("comment", text)
                .response()
                .spec(responseSpecification)
                .log().all()
                .when()
                .post("https://api.imgur.com/3/comment")
                .jsonPath().getInt("data.id");

        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    @Order(2)
    void testGetComment() {
        given()
                .log().all()
                .spec(requestSpecification)
                .response()
                .spec(responseSpecification)
                .body("data.id", is(commentId))
                .body("data.image_id", is(galleryImageHash))
                .body("data.comment", is(text))
                .log().all()
                .when()
                .get("https://api.imgur.com/3/comment/" + commentId);
    }

    @Test
    @Order(3)
    void testDeleteComment() {
        given()
                .log().all()
                .spec(requestSpecification)
                .response()
                .spec(responseSpecification)
                .log().all()
                .when()
                .delete("https://api.imgur.com/3/comment/" + commentId);
    }

    @Test
    @Order(4)
    void testGetCommentNotExist() {
        String actually = given()
                .log().all()
                .spec(requestSpecification)
                .expect()
                .statusCode(404)
                .log().all()
                .when()
                .get("https://api.imgur.com/3/comment/" + commentId)
                .body()
                .prettyPrint();
        Assertions.assertTrue(actually.contains("<title>imgur: the simple 404 page</title>"));
    }

}
