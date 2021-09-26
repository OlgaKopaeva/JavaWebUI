package com.imgur;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTests extends BaseTest {
    private static final String PATH_TO_IMAGE = "src/test/resources/cat.jpg";
    static String encodedFile;
    static String imageHash;
    static String deletehash;

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setAuth(oauth2(token))
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("success", is(true))
            .build();

    @BeforeAll
    static void beforeAllTest() throws IOException {
        File file = new File(PATH_TO_IMAGE);
        encodedFile = encodeFileToBase64(file);
    }

    @Order(1)
    @Test
    @DisplayName("Upload image")
    void testUploadImage() throws InterruptedException {
        JsonPath response = given()
                .log().all()
                .spec(requestSpecification)
                .contentType("multipart/form-data")
                .multiPart("image", encodedFile)
                .headers("type", "base64")
                .response()
                .spec(responseSpecification)
                .log().all()
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract().response()
                .jsonPath();

        deletehash = response.getString("data.deletehash");
        imageHash = response.getString("data.id");

        TimeUnit.SECONDS.sleep(3);
    }

    @Order(2)
    @Test
    @DisplayName("Get image")
    void testGetImage() {
        given()
                .log().all()
                .spec(requestSpecification)
                .response()
                .spec(responseSpecification)
                .log().all()
                .body("data.type", is("image/jpeg"))
                .body("data.deletehash", is(deletehash))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}", imageHash);
    }

    @Order(3)
    @Test
    @DisplayName("Delete image")
    void testDeleteImage() throws InterruptedException {
        given()
                .log().all()
                .spec(requestSpecification)
                .response()
                .spec(responseSpecification)
                .log().all()
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, deletehash);

        TimeUnit.SECONDS.sleep(5);
    }

    @Order(4)
    @Test
    @DisplayName("Get non-existing image")
    void testGetNonExistingImage() {
        String actually = given()
                .log().all()
                .spec(requestSpecification)
                .expect()
                .statusCode(404)
                .log().all()
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}", imageHash)
                .body()
                .prettyPrint();
        Assertions.assertTrue(actually.contains("<title>imgur: the simple 404 page</title>"));
    }

    private static String encodeFileToBase64(File file) throws IOException {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

}
