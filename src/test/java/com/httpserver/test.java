package com.httpserver;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        BigDecimal b50 = new BigDecimal("50");

        BigDecimal b1_5 = new BigDecimal("1.5");
        BigDecimal divide = b50.divide(b1_5, 2);
        System.out.println(divide.toString());
        System.out.println(50 / 1.5);
        System.out.println(b50.compareTo(b1_5)>0);
        System.out.println(b50.multiply(b1_5));
    }
}
