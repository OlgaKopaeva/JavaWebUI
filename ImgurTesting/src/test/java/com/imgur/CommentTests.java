package com.imgur;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTests extends BaseTest {
    static String galleryImageHash = "Fp9I0i5";
    static int commentId;

    @Order(1)
    @Test
    @DisplayName("Post comment")
    void testPostComment() throws InterruptedException {
        commentId = given()
                .log().all()
                .headers("Authorization", "Bearer " + token)
                .formParam("image_id", galleryImageHash)
                .formParam("comment", "Beatiful!")
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.id", Matchers.notNullValue())
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
                .headers("Authorization", "Bearer " + token)
                .log().all()
                .expect()
                .body("data.id", is(commentId))
                .body("data.image_id", is(galleryImageHash))
                .body("data.comment", is("Beatiful!"))
                .statusCode(200)
                .log().all()
                .when()
                .get("https://api.imgur.com/3/comment/" + commentId);
    }

    @Test
    @Order(3)
    void testDeleteComment() {
        given()
                .headers("Authorization", "Bearer " + token)
                .log().all()
                .expect()
                .statusCode(200)
                .log().all()
                .when()
                .delete("https://api.imgur.com/3/comment/" + commentId);
    }

    @Test
    @Order(4)
    void testCommentNotExist() {
        String actually = given()
                .headers("Authorization", "Bearer " + token)
                .log().all()
                .expect()
                .log().all()
                .when()
                .get("https://api.imgur.com/3/comment/" + commentId)
                .body()
                .prettyPrint();
        Assertions.assertTrue(actually.contains("<title>imgur: the simple 404 page</title>"));
    }

}
