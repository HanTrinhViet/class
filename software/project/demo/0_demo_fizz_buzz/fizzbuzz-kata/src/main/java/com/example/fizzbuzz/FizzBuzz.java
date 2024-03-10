package com.example.fizzbuzz;

public class FizzBuzz {
    private static final int FIZZ_NUMBER = 3;
    private static final int BUZZ_NUMBER = 5;

    public String convert(int number) {
        StringBuilder result = new StringBuilder();

        if (number % FIZZ_NUMBER == 0) {
            result.append("Fizz");
        }
        if (number % BUZZ_NUMBER == 0) {
            result.append("Buzz");
        }

        return result.length() == 0 ? Integer.toString(number) : result.toString();
    }
}
