package com.carsales.store;

import com.carsales.model.entity.Car;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Репозиорий для работы с БД and table cars
 * +
 * Wrapper Класс предоставляет SessionFactory через class DBSession
 */
public class CarRepository implements CarRepoStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRepository.class.getName());

    private static final Marker IMPORTANT = MarkerFactory.getMarker("DEBUG");

    private static final CarRepository INST = new CarRepository();

    private final Wrapper wrapper = new Wrapper();

    public static CarRepository instOf() {
        return INST;
    }

    /**
     * - сохранить машину в БД
     *
     * @param car
     * @return
     */
    @Override
    public Car saveCar(Car car) {
        Car rsl = null;
        try {
            Session session = wrapper.getSf().openSession();
            session.beginTransaction();
            var result = session.save(car);
            int index = (int) result;
            car.setId(index);
            session.getTransaction().commit();
            session.close();
            rsl = car;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
