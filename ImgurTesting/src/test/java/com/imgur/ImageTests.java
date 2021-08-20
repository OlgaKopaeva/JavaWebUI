package com.imgur;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ImageTests extends BaseTest {
    private static final String PATH_TO_IMAGE = "src/test/resources/cat.jpg";
    private static final String PATH_TO_IMAGE2 = "src/test/resources/Thinking-of-getting-a-cat.png";
    static String encodedFile;
    static String encodedFile2;

    @BeforeAll
    static void beforeAllTest() throws IOException {
        File file = new File(PATH_TO_IMAGE);
        File file2 = new File(PATH_TO_IMAGE2);
        encodedFile = encodeFileToBase64(file);
        encodedFile2 = encodeFileToBase64(file2);
    }

    @Test
    void uploadFileTest() {
        JsonPath response = given()
                .headers("Authorization", "Bearer " + token)
                .headers("type", "base64")
                .multiPart("image", encodedFile)
                .expect()
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();

        properties.setProperty("deletehash", response.getString("data.deletehash"));
        properties.setProperty("imageID", response.getString("data.id"));

        try (OutputStream output = new FileOutputStream("src/test/resources/application.properties")) {
            properties.store(output, "save properties");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteImageTest() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, deletehash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    private static String encodeFileToBase64(File file) throws IOException {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }


}
