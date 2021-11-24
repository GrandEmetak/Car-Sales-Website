package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.*;
import java.util.function.Function;

/**
 * Реализовать пользовательские фильтры для площадок машин [#4745]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.3. HQL, Criteria
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 */
public class AdRepository implements Store {
    private static final AdRepository INST = new AdRepository();

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static AdRepository instOf() {
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
     * - показать объявления за последний день
     *
     * @return
     */
    @Override
    public List<Post> lastDay() {
        return this.tx(
                session -> {
                    var query = session.createQuery("from ru.job4j.cars.model.Post s where s.created between :date1 and :date2"
                    ).setParameter("date1", subtractDays())
                     .setParameter("date2", new Date(System.currentTimeMillis()));
                    return query.list();
                }
        );
    }

    /**
     * метод определение даты минус 1 день
     *
     * @return
     */
    private Date subtractDays() {
        Date date = new Date(System.currentTimeMillis());
        int days = 1;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    /**
     * - показать объявления с фото
     *
     * @return
     */
    @Override
    public List<Post> whenPhotoTrue() {
        List<Post> postList = new ArrayList<>();
        return this.tx(
                session -> {
                    var query = session.createQuery(
                            "select distinct st from Post st join fetch st.photo a ", Post.class
                    );
                    for (var post : query.list()) {
                        if (post.getPhoto() != null) {
                            postList.add(post);
                        }
                    }
                    return postList;
                }
        );
    }

    /**
     * - показать объявления определенной марки.
     *
     * @param name
     * @return
     */
    @Override
    public List<Post> whenMarkAuto(String name) {
        return this.tx(
                session -> {
                    var query = session.createQuery(
                            "select distinct st from Post st "
                                    + "join fetch st.user a "
                                    + "join fetch st.car b "
                                    + "join fetch st.photo c "
                                    + "where st.car.mark = :sBrandName", Post.class
                    ).setParameter("sBrandName", name);
                    return query.list();
                }
        );
    }

    /**
     * - поиск пользователей по email
     *
     * @param email
     * @return
     */
    @Override
    public List<User> findByEmail(String email) {
        return this.tx(
                session -> {
                    String sql = "from ru.job4j.cars.model.User where email = :email";
                    Query query = session.createQuery(sql);
                    query.setParameter("email", email);
                    return query.list();
                }
        );
    }
}
