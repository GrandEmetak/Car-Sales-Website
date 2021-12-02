package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;

/**
 * Основной класс по работе с БД через hibernate
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
     * save Post Object in to DB Post
     *
     * @param post Object
     * @return Post object
     */
    @Override
    public Post savePost(Post post) {
        if (post.getId() == 0) {
            System.out.println("save Post method Adrepository " + post);
            return saveByPost(post);
        }
        System.out.println(" User update Post Adrepository " + post);
        return updateByPost(post);
    }

    private Post saveByPost(Post post) {
        Post rsl = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            var result = session.save(post);
            System.out.println("То что сохраняем : " + post);
            int index = (int) result;
            post.setId(index);
            session.getTransaction().commit();
            session.close();
            rsl = post;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return rsl;
    }

    private Post updateByPost(Post post) {
        Post rsl = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            session.saveOrUpdate(post);
            System.out.println("То что обновляем : " + post);
            session.getTransaction().commit();
            session.close();
            rsl = post;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return rsl;
    }

    @Override
    public Photo savePhoto(Photo photo) {
        Photo rsl = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            var result = session.save(photo);
            System.out.println("То что сохраняем : " + photo);
            int index = (int) result;
            photo.setId(index);
            rsl = photo;
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return rsl;
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
            Session session = sf.openSession();
            session.beginTransaction();
            var result = session.save(car);
            System.out.println("То что сохраняем : " + car);
            int index = (int) result;
            car.setId(index);
            session.getTransaction().commit();
            session.close();
            rsl = car;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return rsl;
    }

    /**
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
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return rsl;
    }

    /**
     * - показать все объявления из БД posts
     *
     * @return
     */
    @Override
    public List<Post> findAllPost() {
        return this.tx(
                session -> {
                    var query = session.createQuery("from ru.job4j.cars.model.Post");
                    return query.list();
                }
        );
    }

    /**
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
     * - показать объявления за последний день
     *
     * @return Collection List<> Post object
     */
    @Override
    public List<Post> lastDay() {
        return this.tx(
                session -> {
                    var query = session.createQuery("from ru.job4j.cars.model.Post s"
                            + " where s.created between :start and :finish"
                    )
                            .setParameter("start", subtractDays())
                            .setParameter("finish", new Date(System.currentTimeMillis()));
                    return query.list();
                }
        );
    }

    /**
     * метод определение даты минус 1 день
     *
     * @return Date time one day before
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
     * @return Collection List<> Post object
     */
    @Override
    public List<Post> whenPhotoTrue() {
        List<Post> postList = new ArrayList<>();
        return this.tx(
                session -> {
                    var query = session.createQuery(
                            "select distinct st from Post st join fetch st.photo a "
                                    + "where st.photo is not null ", Post.class
                    );
                    return query.list();
                }
        );
    }

    /**
     * - показать объявления определенной марки.
     *
     * @param name auto mark
     * @return Collection List<> Post object
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
     * - найти Пост по id
     *
     * @param id
     * @return
     */
    @Override
    public List<Post> findPostBiId(int id) {
        return this.tx(
                session -> {
                    var query = session.createQuery(
                            "select distinct st from Post st "
                                    + "join fetch st.user a "
                                    + "join fetch st.car b "
                                    + "join fetch st.photo c "
                                    + "where st.id = :sId", Post.class
                    ).setParameter("sId", id);
                    return query.list();
                }
        );
    }

    /**
     * найти все посты Юзера по его id
     *
     * @param id
     * @return
     */
    @Override
    public List<Post> findPostByUserId(int id) {
        List<Post> postList = new ArrayList<>();
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            var query = session.createQuery(
                    "select distinct st from Post st "
                            + "join fetch st.user a "
                            + "join fetch st.car b "
                            + "join fetch st.photo c "
                            + "where st.user.id = :sId", Post.class
            ).setParameter("sId", id);
            postList = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return postList;
    }

    /**
     * найти Пост по id User + Пост header
     *
     * @param id
     * @param header
     * @return
     */
    @Override
    public List<Post> findPostByUserIdAndHeader(int id, String header) {
        return this.tx(
                session -> {
                    var query = session.createQuery(
                            "select distinct st from Post st "
                                    + "join fetch st.user a "
                                    + "join fetch st.car b "
                                    + "join fetch st.photo c "
                                    + "where st.user.id = :sId"
                                    + " and st.header = :head", Post.class
                    ).setParameter("sId", id)
                            .setParameter("head", header);
                    return query.list();
                }
        );
    }

    /**
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

    /**
     * найти и удалить пользователя по id
     *
     * @param id
     * @return
     */
    @Override
    public boolean deletePostId(int id) {
        boolean rsl = false;
        if (id != 0) {
            try {
                Session session = sf.openSession();
                Transaction tx = session.beginTransaction();
                Post post = (Post) session.load(Post.class, id);
                session.delete(post);
                session.getTransaction().commit();
                session.close();
                rsl = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return rsl;
    }

    /**
     * метод приводит дату к общепринятому виду
     * Tue Nov 30 19:44:46 YEKT 2021
     *
     * @param date
     * @return
     */
    public Date convertDays(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.getTime();
    }
}

