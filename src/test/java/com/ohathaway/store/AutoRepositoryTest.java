package com.ohathaway.store;

import com.ohathaway.model.entity.Auto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutoRepositoryTest {

    @Test
    public void whenSaveCar() {
        CarRepository carRepository = new CarRepository();
        Auto auto = Auto.of("Ford F500",
                "TRUCK",
                "бензин, 3.5 л., 238 л.с., налог ",
                "АКПП",
                "белый",
                "4WD",
                "17 432"
        );
        Auto auto1 = carRepository.saveCar(auto);

        assertEquals(auto.getEngine(), auto1.getEngine());
    }
}