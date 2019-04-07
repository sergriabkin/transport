package com.company.service;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;
import com.company.repository.TrainRepository;
import com.company.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainServiceImpl implements TrainService {

    private TrainRepository train;

    public TrainServiceImpl(TrainRepository train) {
        this.train = train;
    }

    @Override
    public List<Carriage> getTrain() {
        return train.getTrain();
    }

    @Override
    public void setTrain(List<Carriage> train) {
        this.train.setTrain(train);
    }

    @Override
    public Carriage addCarriage(CarriageType type) {
        return train.addCarriage(type);
    }

    @Override
    public int countAllPassengers() {
        return (int) getTrain().stream()
                .flatMap(carriage -> Stream.of(carriage.getPassengers()))
                .filter(Objects::nonNull)
                .count();
    }

    @Override
    public int countAllLuggage() {
        return (int) getTrain().stream()
                .flatMap(carriage -> Stream.of(carriage.getLuggage()))
                .filter(Objects::nonNull)
                .count();
    }

    @Override
    public void sortByComfortLevel() {
        List<Carriage> sortedTrain = getTrain().stream()
                .sorted(Comparator.comparing(Carriage::getComfortLevel))
                .collect(Collectors.toList());
        setTrain(sortedTrain);
    }

    @Override
    public List<Carriage> findByPassengersCount(int from, int to) {
        return getTrain().stream()
                .filter(carriage -> {
                    int count = Util.countNotEmptyPassengersPlaces(carriage);
                    return count >= from && count <= to;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Carriage populateCarriageWithPassengers(int carriageId, int percentOfPassengersFilling) {
        return train.populateCarriageWithPassengers(carriageId, percentOfPassengersFilling);
    }

    @Override
    public Carriage populateCarriageWithLuggage(int carriageId, int percentOfPassengersFilling) {
        return train.populateCarriageWithLuggage(carriageId, percentOfPassengersFilling);
    }



}
