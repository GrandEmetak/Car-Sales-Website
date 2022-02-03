package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.connect.DBSession;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.function.Function;

/**
 * Слой репозиторий по работе с БД and table users
 */
public class UserRepository implements  UserRepoStore {
    private static final UserRepository INST = new UserRepository();

    private final SessionFactory sf =  DBSession.getInstance().getSessionFactory();

    public static UserRepository instOf() {
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
     * RegServlet doPost usage
     * - put User in to DB cars
     *
     * @param user Object to paste
     * @return Object User(includes new id) or User null Object
     */
    @Override
    public User saveUser(User user) {
        User rsl = null;
        try {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            var result = session.save(user);
            int index = (int) result;
            user.setId(index);
            rsl = user;
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * +++
     * - find User object by id
     *
     * @param id User Object
     * @return List User Object
     */
    @Override
    public List<User> findUserById(int id) {
        return this.tx(
                session -> {
                    var query = session.createQuery("from ru.job4j.cars.model.User s "
                            + "where s.id = :sId")
                            .setParameter("sId", id);
                    return query.list();
                }
        );
    }

    /**
     * +++
     * Authservle
     * Regservlet
     * - поиск пользователей по email
     *
     * @param email
     * @return Collection List<> User object
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
