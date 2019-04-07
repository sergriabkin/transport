package com.company.entity;

import java.util.Arrays;

public class Carriage {
    private int carriageId;
    private int comfortLevel;
    private int passengerCapacity;
    private int luggageCapacity;

    private Passenger[] passengers;
    private Luggage[] luggage;

    private static int counter = 1;

    public Carriage(int comfortLevel, int passengerCapacity, int luggageCapacity) {
        this.carriageId = counter++;
        this.comfortLevel = comfortLevel;
        this.passengerCapacity = passengerCapacity;
        this.luggageCapacity = luggageCapacity;
        this.passengers = new Passenger[passengerCapacity];
        this.luggage = new Luggage[luggageCapacity];
    }

    public int getCarriageId() {
        return carriageId;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getLuggageCapacity() {
        return luggageCapacity;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public Carriage setPassengers(Passenger... passengers) {
        this.passengers = passengers;
        return this;
    }

    public Luggage[] getLuggage() {
        return luggage;
    }

    public Carriage setLuggage(Luggage... luggage) {
        this.luggage = luggage;
        return this;
    }


    @Override
    public String toString() {
        return "Carriage{" +
                "carriageId=" + carriageId +
                ", comfortLevel=" + comfortLevel +
                ", passengerCapacity=" + passengerCapacity +
                ", luggageCapacity=" + luggageCapacity +
                '}';
    }
}
