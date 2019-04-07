package com.company.repository;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;

import java.util.List;

public interface TrainRepository {
    List<Carriage> getTrain();

    void setTrain(List<Carriage> train);

    Carriage addCarriage(CarriageType type);

    Carriage populateCarriageWithPassengers(int carriageId, int percentOfPassengersFilling);

    Carriage populateCarriageWithLuggage(int carriageId, int percentOfLuggageFilling);


}
