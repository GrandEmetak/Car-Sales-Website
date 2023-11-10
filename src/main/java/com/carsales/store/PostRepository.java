package com.carsales.store;

import com.carsales.model.entity.Photo;
import com.carsales.model.entity.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.*;

/**
 * Основной класс по работе с БД через hibernate
 * +
 * Wrapper Класс предоставляет SessionFactory через class DBSession
 * Реализованы пользовательские фильтры для площадок машин
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 * и др.
 */
public class PostRepository implements Store {

    private static final PostRepository INST = new PostRepository();

    private static final Logger LOGGER = LoggerFactory.getLogger(PostRepository.class.getName());

    private static final Marker INFO = MarkerFactory.getMarker("INFO");

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final Wrapper wrapper = new Wrapper();

    public static PostRepository instOf() {
        return INST;
    }


    @Override
    public List<Post> findAllPost() {
        return this.wrapper.tx(
                session -> {
                    var query = session.createQuery("SELECT p FROM Post p ORDER BY p.id ASC");
                    return query.list();
                }
        );
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
            return saveByPost(post);
        }
        return updateByPost(post);
    }

    private Post saveByPost(Post post) {
        Post rsl = null;
        try {
            Session session = wrapper.getSf().openSession();
            session.beginTransaction();
            var result = session.save(post);
            int index = (int) result;
            post.setId(index);
            session.getTransaction().commit();
            session.close();
            rsl = post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private Post updateByPost(Post post) {
        Post rsl = null;
        try {
            Session session = wrapper.getSf().openSession();
            session.beginTransaction();
            session.saveOrUpdate(post);

            session.getTransaction().commit();
            session.close();
            rsl = post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Photo savePhoto(Photo photo) {
        Photo rsl = null;
        try {
            Session session = wrapper.getSf().openSession();
            session.beginTransaction();
            var result = session.save(photo);
            int index = (int) result;
            photo.setId(index);
            rsl = photo;
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * - показать объявления за последний день
     *
     * @return Collection List Post object
     */
    @Override
    public List<Post> lastDay() {
        return this.wrapper.tx(
                session -> {
                    var query = session.createQuery("SELECT p FROM Post p"
                            + " where p.created between :start and :finish "
                            + "order by p.created"
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
     * @return Collection List Post object
     */
    @Override
    public List<Post> whenPhotoTrue() {
        List<Post> postList = new ArrayList<>();
        return this.wrapper.tx(
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
     * @return Collection List Post object
     */
    @Override
    public List<Post> whenMarkAuto(String name) {
        return this.wrapper.tx(
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
     * - find Post Object to id  найти Пост по id
     *
     * @param id Post object
     * @return ListPost
     */
    @Override
    public List<Post> findPostBiId(int id) {
        List<Post> postList = new ArrayList<>();
        try {
            Session session = wrapper.getSf().openSession();
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
     * @return List Post
     */
    @Override
    public List<Post> findPostByUserId(int id) {
        List<Post> postList = new ArrayList<>();
        try {
            Session session = wrapper.getSf().openSession();
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
     */
    @Override
    public List<Post> findPostByUserIdAndHeader(int id, String header) {
        return this.wrapper.tx(
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
     */
    @Override
    public boolean deletePostId(int id) {
        boolean rsl = false;
        if (id != 0) {
            try {
                Session session = wrapper.getSf().openSession();
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
     */
    public Date convertDays(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.getTime();
    }
}

