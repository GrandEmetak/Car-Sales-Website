package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ru.job4j.cars.model.User;

import java.util.List;

/**
 * Слой репозиторий по работе с БД and table users
 * +
 * Wrapper Класс предоставляет SessionFactory через class DBSession
 */
public class UserRepository implements UserRepoStore {

    private static final UserRepository INST = new UserRepository();

    private final Wrapper wrapper = new Wrapper();

    public static UserRepository instOf() {
        return INST;
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
            Session session = wrapper.getSf().openSession();
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
        return this.wrapper.tx(
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
        return this.wrapper.tx(
                session -> {
                    String sql = "from ru.job4j.cars.model.User where email = :email";
                    Query query = session.createQuery(sql);
                    query.setParameter("email", email);
                    return query.list();
                }
        );
    }
}
