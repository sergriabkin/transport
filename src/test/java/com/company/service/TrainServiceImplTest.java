package com.company.service;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;
import com.company.repository.TrainFromPropertiesRepositoryImpl;
import com.company.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class TrainServiceImplTest {

    private final TrainServiceImpl TRAIN_SERVICE = new TrainServiceImpl(new TrainFromPropertiesRepositoryImpl());
    private final Properties CARRIAGE_PROPERTIES = Util.getProperties("carriageProperties", this);

    private int people = 0;
    private int luggage = 0;

    @Test
    public void shouldCountAllPassengers() {
        int actual = TRAIN_SERVICE.countAllPassengers();
        assertEquals(people, actual);
    }

    @Test
    public void shouldCountAllLuggage() {
        int actual = TRAIN_SERVICE.countAllLuggage();
        assertEquals(luggage, actual);
    }

    @Test
    public void shouldSortByComfortLevel() {
        List<Carriage> unsortedTrain = TRAIN_SERVICE.getTrain();
        TRAIN_SERVICE.sortByComfortLevel();
        List<Carriage> sortedTrain = TRAIN_SERVICE.getTrain();

        assertEquals(sortedTrain.size(), unsortedTrain.size());

        int currentComfortLevel = 0;
        for (Carriage carriage : sortedTrain) {
            int nextComfortLevel = carriage.getComfortLevel();
            assertTrue(currentComfortLevel <= nextComfortLevel);
            currentComfortLevel = nextComfortLevel;
        }
    }

    @Test
    public void shouldReturnCarriageListFilteredByPassagesAmounts(){
        int from = 20;
        int to = 30;
        List<Carriage> train = TRAIN_SERVICE.findByPassengersCount(from, to);
        for (Carriage carriage : train) {
            int passengers = Util.countNotEmptyPassengersPlaces(carriage);
            assertTrue( passengers >= from);
            assertTrue(passengers <= to);
        }
    }


    @Before
    public void populateTrain() {
        Properties properties = Util.getProperties("populateTrain", TRAIN_SERVICE);
        int id1 = TRAIN_SERVICE.addCarriage(CarriageType.LOCOMOTIVE).getCarriageId();
        int id2 = TRAIN_SERVICE.addCarriage(CarriageType.CLASS_2).getCarriageId();
        int id3 = TRAIN_SERVICE.addCarriage(CarriageType.CLASS_1).getCarriageId();
        int id4 = TRAIN_SERVICE.addCarriage(CarriageType.CLASS_3).getCarriageId();

        int testPercent = Integer.parseInt(properties.getProperty("option10"));

        TRAIN_SERVICE.populateCarriageWithPassengers(id2, testPercent); // 0+36
        people += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("passengersInClass2")) * testPercent) / 100;
        TRAIN_SERVICE.populateCarriageWithPassengers(id3, testPercent); // 36+18
        people += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("passengersInClass1")) * testPercent) / 100;
        TRAIN_SERVICE.populateCarriageWithPassengers(id4, testPercent); // 54+54 = 108
        people += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("passengersInClass3")) * testPercent) / 100;

        TRAIN_SERVICE.populateCarriageWithLuggage(id2, testPercent); // 0+54
        luggage += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("luggageInClass2")) * testPercent) / 100;
        TRAIN_SERVICE.populateCarriageWithLuggage(id3, testPercent); // 54+54
        luggage += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("luggageInClass1")) * testPercent) / 100;
        TRAIN_SERVICE.populateCarriageWithLuggage(id4, testPercent); // 108+54 = 162
        luggage += (Integer.parseInt(CARRIAGE_PROPERTIES.getProperty("luggageInClass3")) * testPercent) / 100;

    }

    @After
    public void clearTrain() {
        TRAIN_SERVICE.setTrain(new ArrayList<>());
        people = 0;
        luggage = 0;
    }
}