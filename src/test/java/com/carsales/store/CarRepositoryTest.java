package com.carsales.store;

import com.carsales.model.Car;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarRepositoryTest {

    @Test
    public void whenSaveCar() {
        CarRepository carRepository = new CarRepository();
        Car car = Car.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Car car1 = carRepository.saveCar(car);

        assertEquals(car.getEngine(), car1.getEngine());
    }
}