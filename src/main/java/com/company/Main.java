package com.company;

import com.company.entity.Carriage;
import com.company.entity.CarriageType;
import com.company.repository.TrainFromPropertiesRepositoryImpl;
import com.company.service.TrainService;
import com.company.service.TrainServiceImpl;
import com.company.ui.ConsoleUI;
import com.company.util.Util;

import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        TrainService service = new TrainServiceImpl(new TrainFromPropertiesRepositoryImpl());
        ConsoleUI ui = new ConsoleUI(service);

        populateTrain(service);

        int passengersSum = service.countAllPassengers();
        System.out.println(passengersSum);
        int luggageSum = service.countAllLuggage();
        System.out.println(luggageSum);

        service.sortByComfortLevel();
        System.out.println(service.getTrain());
        List<Carriage> carriagesWithPassengersCountBetween = service.findByPassengersCount(20, 40);
        carriagesWithPassengersCountBetween.forEach(System.out::println);

    }

    private static void populateTrain(TrainService service) {
        Properties properties = Util.getProperties("populateTrain", service);
        int id1 = service.addCarriage(CarriageType.LOCOMOTIVE).getCarriageId();
        int id2 = service.addCarriage(CarriageType.CLASS_2).getCarriageId();
        int id3 = service.addCarriage(CarriageType.CLASS_1).getCarriageId();
        int id4 = service.addCarriage(CarriageType.CLASS_3).getCarriageId();
        int id5 = service.addCarriage(CarriageType.CLASS_2).getCarriageId();
        int id6 = service.addCarriage(CarriageType.CLASS_3).getCarriageId();
        int id7 = service.addCarriage(CarriageType.LOCOMOTIVE).getCarriageId();

        service.populateCarriageWithPassengers(id2, Integer.parseInt(properties.getProperty("option2")));
        service.populateCarriageWithPassengers(id3, Integer.parseInt(properties.getProperty("option6")));
        service.populateCarriageWithPassengers(id4, Integer.parseInt(properties.getProperty("option4")));
        service.populateCarriageWithPassengers(id5, Integer.parseInt(properties.getProperty("option7")));
        service.populateCarriageWithPassengers(id6, Integer.parseInt(properties.getProperty("option1")));

        service.populateCarriageWithLuggage(id2, Integer.parseInt(properties.getProperty("option7")));
        service.populateCarriageWithLuggage(id3, Integer.parseInt(properties.getProperty("option5")));
        service.populateCarriageWithLuggage(id4, Integer.parseInt(properties.getProperty("option6")));
        service.populateCarriageWithLuggage(id5, Integer.parseInt(properties.getProperty("option3")));
        service.populateCarriageWithLuggage(id6, Integer.parseInt(properties.getProperty("option8")));
    }
}
