package ru.job4j.cars.model;

import javax.persistence.GenerationType;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Модель данных описывает пользователя/владельца объявления(Post)
 * Реализовать площадку продаж машин. [#4747]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * - Спроектируйте SQL схему для сайта по продажам машин.
 * - Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.
 * - Загрузите схему в корень репозитория в папку db.
 *
 * @author SlartiBartFast-art
 * @version 01
 * @since 22.11.21
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_id")
//    private List<Car> carList = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_id")
//    private List<Post> postList = new ArrayList<>();

    public User() {
    }

    public static User of(String name, String email, String password) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        return user;
    }

//    public void addCars(Car car) {
//        this.carList.add(car);
//    }
//
//    public void addPost(Post ps) {
//        this.postList.add(ps);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Car> getCarList() {
//        return carList;
//    }
//
//    public void setCarList(List<Car> carList) {
//        this.carList = carList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
