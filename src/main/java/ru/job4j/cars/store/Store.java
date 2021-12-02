package ru.job4j.cars.store;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.List;

/**
 * Интерфейс, общие методы для работы с сущностями
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 * - поиск пользователей по email
 *  - и др.
 * @author SlartiBartFast-art
 * @version 1
 * @since 26.11.21
 */
public interface Store {

    public User saveUser(User user);

    public Post savePost(Post post);

    public List<Post> findAllPost();

    public List<Post> lastDay();

    public List<Post> whenPhotoTrue();

    public List<Post> whenMarkAuto(String name);

    public List<User> findByEmail(String email);

    public List<User> findUserById(int id);

    public List<Post> findPostBiId(int id);

    public boolean deletePostId(int id);

    public Photo savePhoto(Photo photo);

    public Car saveCar(Car car);

    public List<Post> findPostByUserId(int id);

    public List<Post> findPostByUserIdAndHeader(int id, String header);

}
