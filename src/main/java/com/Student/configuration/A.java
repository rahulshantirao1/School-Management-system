package com.Student.configuration;

public class A {

    public static void main(String[] args) {
        test(3,4,5,3,3);
    }
    static void test(int ...x){
        for (int y:x) {
            System.out.println(y);
        }
    }
}
