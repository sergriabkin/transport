package com.company.entity;

public class Luggage {
    private int luggageId;

    private static int counter = 1;

    public Luggage(int luggageId) {
        this.luggageId = counter++;
    }
}
