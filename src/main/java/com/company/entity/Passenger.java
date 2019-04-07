package com.company.entity;

public class Passenger {
    private int passengerId;

    private static int counter = 1;

    public Passenger(int passengerId) {
        this.passengerId = counter++;
    }
}
