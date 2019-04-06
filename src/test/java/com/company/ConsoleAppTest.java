package com.company;

import org.junit.Test;

import static com.company.ConsoleApp.sum;
import static org.junit.Assert.assertEquals;

public class ConsoleAppTest {

    @Test
    public void shouldReturnSum(){
        int actual = sum(1, 2);
        int expected = 3;

        assertEquals(expected, actual);
    }
}
