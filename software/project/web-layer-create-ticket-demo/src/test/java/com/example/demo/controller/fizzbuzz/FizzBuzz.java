package com.example.demo.controller.fizzbuzz;

public class FizzBuzz {
    public String convert(int number) {
        if (number % 3 == 0) {
            return "Fizz";
        }
        return String.valueOf(number);
    }
}