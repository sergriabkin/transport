package com.company.repository;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;
import com.company.entity.Luggage;
import com.company.entity.Passenger;
import com.company.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class TrainFromPropertiesRepositoryImpl implements TrainRepository {

    private List<Carriage> train;
    private Properties properties;

    public TrainFromPropertiesRepositoryImpl() {
        this.train = new ArrayList<>();
        this.properties = Util.getProperties("carriageProperties", this);
    }

    @Override
    public List<Carriage> getTrain() {
        return train;
    }

    @Override
    public void setTrain(List<Carriage> train) {
        this.train = train;
    }

    @Override
    public Carriage addCarriage(CarriageType type) {
        Carriage carriage = createCarriage(type);
        train.add(carriage);
        return carriage;
    }

    @Override
    public Carriage populateCarriageWithPassengers(int carriageId, int percentOfPassengersFilling) {

        Carriage carriage = Objects.requireNonNull(findCarriageById(carriageId));
        Passenger[] passengers = new Passenger[carriage.getPassengerCapacity()];

        if (percentOfPassengersFilling > 100) percentOfPassengersFilling = 100;
        if (percentOfPassengersFilling < 0) percentOfPassengersFilling = 0;

        for (int i = 0; i < passengers.length * percentOfPassengersFilling / 100; i++) {
            passengers[i] = new Passenger(i);
        }

        return carriage.setPassengers(passengers);
    }

    @Override
    public Carriage populateCarriageWithLuggage(int carriageId, int percentOfLuggageFilling) {

        Carriage carriage = Objects.requireNonNull(findCarriageById(carriageId));
        Luggage[] luggage = new Luggage[carriage.getLuggageCapacity()];

        if (percentOfLuggageFilling > 100) percentOfLuggageFilling = 100;
        if (percentOfLuggageFilling < 0) percentOfLuggageFilling = 0;

        for (int i = 0; i < luggage.length * percentOfLuggageFilling / 100; i++) {
            luggage[i] = new Luggage(i);
        }

        return carriage.setLuggage(luggage);
    }


    private Carriage findCarriageById(int carriageId) {
        for (Carriage carriage : train) {
            if (carriageId == carriage.getCarriageId()) return carriage;
        }
        return null;
    }


    private Carriage createCarriage(CarriageType type) {
        switch (type) {
            case CLASS_1:
                return new Carriage(3,
                        Integer.parseInt(properties.getProperty("passengersInClass1")),
                        Integer.parseInt(properties.getProperty("luggageInClass1")));
            case CLASS_2:
                return new Carriage(2,
                        Integer.parseInt(properties.getProperty("passengersInClass2")),
                        Integer.parseInt(properties.getProperty("luggageInClass2")));
            case CLASS_3:
                return new Carriage(1,
                        Integer.parseInt(properties.getProperty("passengersInClass3")),
                        Integer.parseInt(properties.getProperty("luggageInClass3")));
            case LOCOMOTIVE:
                return new Carriage(0, 0, 0);
            default:
                throw new IllegalArgumentException();
        }

    }

}
