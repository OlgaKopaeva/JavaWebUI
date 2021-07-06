package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Triangle implements GeometricFigure {
    private static Logger logger = LoggerFactory.getLogger(Triangle.class);

    public float firstSideLength;
    public float secondSideLength;
    public float thirdSideLength;

    public Triangle(){
        this.firstSideLength = 1.0F;
        this.secondSideLength = 1.0F;
        this.thirdSideLength = 1.0F;
    }

    @Override
    public double calculateArea(float a, float b, float c) {
        if (!isPossible(a,b,c)) {
            logger.info("Triangle is impossible.");
            return 0.0;
        }
        float p;
        p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public boolean isPossible(float a, float b, float c) {
        return ((a > 0) && (b > 0) && (c > 0) && (a + b > c) && (a + c > b) && (b + c > a));
    }

}
