package com.company.util;

import com.company.entity.Carriage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

public class Util {

    public static Properties getProperties(String name, Object o) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(Objects.requireNonNull(o.getClass().getClassLoader()
                    .getResource(name)).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static int countNotEmptyPassengersPlaces(Carriage carriage) {
        return (int) Stream.of(carriage.getPassengers())
                .filter(Objects::nonNull)
                .count();
    }
}
