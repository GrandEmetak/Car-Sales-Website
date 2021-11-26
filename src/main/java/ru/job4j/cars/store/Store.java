package ru.job4j.cars.store;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.List;

/**
 * Интерфейс, общие методы для работы с сущностями
 * - показать объявления за последний день
 * - показать объявления с фото
 * - показать объявления определенной марки.
 * - поиск пользователей по email
 */
public interface Store {

    public User saveUser(User user);

    public void savePost();

    public List<Post> findAllPost();

    public List<Post> lastDay();

    public List<Post> whenPhotoTrue();

    public List<Post> whenMarkAuto(String name);

    public List<User> findByEmail(String email);

    public List<Post> findPostBiId(int id);

}
