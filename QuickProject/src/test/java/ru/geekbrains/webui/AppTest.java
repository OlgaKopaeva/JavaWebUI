package ru.geekbrains.webui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.geekbrains.webui.App.expon;
import static ru.geekbrains.webui.App.sum;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testSum(){
        int a=2;
        int b=5;
        int expected = 7;
        assertEquals(App.sum(a,b), expected);
    }

    @Test
    public void testDiv(){
        int a = 10;
        int b = 5;
        int expected = 2;
        int result = App.easyDiv(a, b);
        assertEquals(result, expected);
    }

    @Test
    public void testDivByZero(){
        int a = 10;
        int b = 0;
        int expected = -1;
        int result = App.easyDiv(a, b);
        assertEquals(result, expected);
    }

    @Test
    public void testMult(){
        int a = 2;
        int b = -9;
        int expected = -18;
        int result = a * b;
        assertEquals(result, expected);
    }

    @Test
    public void testExpon(){
        int a = 2;
        int b = 2;
        int expected = 4;
        int result = App.expon(a,b);
        assertEquals(result, expected);
    }

    @Test
    public void testExponZero(){
        int a = 6;
        int b = 0;
        int expected = 1;
        int result = App.expon(a,b);
        assertEquals(result, expected);
    }

    @Test
    public void testExponNegatA(){
        int a = -2;
        int b = 3;
        int expected = -8;
        int result = App.expon(a,b);
        assertEquals(result, expected);
    }

    @Test
    public void testExponNegatB(){
        int a = 3;
        int b = -2;
        int expected = 0;
        int result = App.expon(a,b);
        assertEquals(result, expected);
    }


}
