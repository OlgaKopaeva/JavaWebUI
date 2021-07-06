package ru.gb;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class AreaTests {
    Triangle triangle = new Triangle();
    private static Logger logger = LoggerFactory.getLogger(AreaTests.class);

    @BeforeAll
    static void beforeAllTests() {
        logger.info("Start tests");
    }

    @AfterAll
    static void afterAllTests() {
        logger.info("Finish tests");
    }

    @ParameterizedTest
    @DisplayName("Check if triangle is impossible")
    @ValueSource(floats = {5.0F, -3.5F, 0})
    void areaTriangleNegativeTests(float a) {
        double result = triangle.calculateArea(a, 2.0F, 2.0F);
        assertThat(result).as("Triangle is impossible").isEqualTo(0);
    }

    @Test
    @DisplayName("Positive test")
    void areaTrianglePositiveTest() {
        double result = triangle.calculateArea(2.0F, 2.0F, 2.0F);
        assertThat(result).isEqualTo(Math.sqrt(3.0F));
    }
}
