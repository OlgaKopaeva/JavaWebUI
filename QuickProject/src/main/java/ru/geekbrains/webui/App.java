package ru.geekbrains.webui;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static int sum(int a, int b){
        return a + b;
    }

    public static int easyDiv(int a, int b) {
        return b==0 ? -1 : a / b;
    }

    public static int mult(int a, int b){
        return a * b;
    }

    public static int expon(int a, int b) {
        int result = a;
        if (b==0) return 1;
        if (b<0) return 0;
        for (int i = 0; i < b-1; i++) {
            result *= a;
        }
        return result;
    }


}
