package com.example.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the kata FizzBuzz.
 */
public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setup() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    void testNumberNotMultipleOfThreeOrFive() {
        assertEquals("1", fizzBuzz.convert(1));
        assertEquals("2", fizzBuzz.convert(2));
    }

    @Test
    void testNumberMultipleOfThree() {
        assertEquals("Fizz", fizzBuzz.convert(3));
    }

    @Test
    void testNumberMultipleOfFive() {
        assertEquals("Buzz", fizzBuzz.convert(5));
    }

    @Test
    void testNumberMultipleOfThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuzz.convert(15));
    }
}
