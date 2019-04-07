package com.company.service;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;

import java.util.List;

public interface TrainService {
    List<Carriage> getTrain();

    void setTrain(List<Carriage> train);

    Carriage addCarriage(CarriageType type);

    int countAllPassengers();

    int countAllLuggage();

    void sortByComfortLevel();

    List<Carriage> findByPassengersCount(int from, int to);

    Carriage populateCarriageWithPassengers(int carriageId, int percentOfPassengersFilling);

    Carriage populateCarriageWithLuggage(int carriageId, int percentOfLuggageFilling);



}
