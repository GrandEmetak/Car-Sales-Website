package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import ru.job4j.cars.connect.DBSession;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;

import java.util.*;
import java.util.function.Function;

/**
 * Основной класс по работе с БД через hibernate
 * Реализовать пользовательские фильтры для площадок машин [#4745]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.3. HQL, Criteria
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 * Реализовать площадку продаж машин. [#4747]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * Logger LOGGER = Logger.getLogger(имя класса для которго мы получаем Логгер);
 */
public class PostRepository implements Store {

    private static final PostRepository INST = new PostRepository();

    private static final Logger LOGGER = LoggerFactory.getLogger(PostRepository.class.getName());

    private static final Marker INFO = MarkerFactory.getMarker("INFO");

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private static SessionFactory sf = DBSession.getInstance();

    public static PostRepository instOf() {
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
            LOGGER.debug(DEBUG, "debug -> save Post method PostRepository {}", post);
            return saveByPost(post);
        }
        LOGGER.info(INFO, " User update Post PostRepository ", post);
        return updateByPost(post);
    }

    private Post saveByPost(Post post) {
        Post rsl = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            var result = session.save(post);
            int index = (int) result;
            post.setId(index);
            session.getTransaction().commit();
            session.close();
            rsl = post;
            LOGGER.info(INFO, "То что сохраняем : {}", post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private Post updateByPost(Post post) {
        Post rsl = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            session.saveOrUpdate(post);

            session.getTransaction().commit();
            session.close();
            rsl = post;
            String str = "То что обновляем : ";
            LOGGER.info(INFO, str, post);
        } catch (Exception e) {
            e.printStackTrace();
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
            int index = (int) result;
            photo.setId(index);
            rsl = photo;
            session.getTransaction().commit();
            session.close();
            LOGGER.info(INFO, "То что сохраняем : ", photo);
        } catch (Exception e) {
            e.printStackTrace();
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
                    LOGGER.debug(DEBUG, "info -> all Post method PostRepository {}");
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
     * - find Post Object to id
     * найти Пост по id
     *
     * @param id Post object
     * @return List<Post>
     */
    @Override
    public List<Post> findPostBiId(int id) {
        List<Post> postList = new ArrayList<>();
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            var query = session.createQuery(
                    "select distinct st from Post st "
                            + "join fetch st.user a "
                            + "join fetch st.car b "
                            + "join fetch st.photo c "
                            + "where st.id = :sId", Post.class
            ).setParameter("sId", id);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
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
            LOGGER.info(INFO, "Все что нашли Посты по Юзер ID");
        } catch (Exception e) {
            e.printStackTrace();
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
     * найти и удалить пост по id
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

