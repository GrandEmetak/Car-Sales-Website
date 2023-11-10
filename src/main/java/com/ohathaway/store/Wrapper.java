package com.ohathaway.store;

import com.ohathaway.connect.DBSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

/**
 * Класс предоставляет SessionFactory через class DBSession,
 * на основе Singleton Data Base connection
 * +
 * метод на основе Лямбды и шаблона wrapper.
 */
public class Wrapper {

    private static final UserRepository INST = new UserRepository();

    private final SessionFactory sf = DBSession.getInstance();

    public static UserRepository instOf() {
        return INST;
    }

    public SessionFactory getSf() {
        return sf;
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
     * @param <T> Он принимает в качестве входных данных параметр T типа
     *
     * @return выдает выходной объект типа R.
     */
    public <T> T tx(final Function<Session, T> command) {
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
}
