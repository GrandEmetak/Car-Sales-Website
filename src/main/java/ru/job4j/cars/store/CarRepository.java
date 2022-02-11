package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import ru.job4j.cars.connect.DBSession;
import ru.job4j.cars.model.Car;

import java.util.function.Function;

/**
 * Репозиорий для работы с БД and table cars
 */
public class CarRepository implements CarRepoStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRepository.class.getName());

    private static final Marker IMPORTANT = MarkerFactory.getMarker("DEBUG");

    private static final CarRepository INST = new CarRepository();

    private final SessionFactory sf =  DBSession.getInstance();

    public static CarRepository instOf() {
        return INST;
    }

    /**
     * Лямбды и шаблон wrapper.
     * Здесь мы можем применить шаблон проектирования wrapper.
     * Function<T,R>
     * Функциональный интерфейс Function<T,R> представляет функцию перехода
     * от объекта типа T к объекту типа R
     * Метод apply()- это основной абстрактный функциональный метод Function интерфейса.
     * Он принимает в качестве входных данных параметр T типа  и выдает выходной объект типа R.
     * Interface EntityTransaction.commit()
     * - Зафиксируйте текущую транзакцию ресурса, записав все незатронутые изменения в базу данных.
     *
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
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
        LOGGER.debug(IMPORTANT, "То что сохраняем : ", car);
        try {
            Session session = sf.openSession();
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
