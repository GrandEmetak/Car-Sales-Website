package ru.job4j.cars.model;

import javax.persistence.GenerationType;
import javax.persistence.*;

import java.util.Objects;

/**
 * Модель данных описывающая Автомобиль
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
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mark;

    private String bodyType;

    @ManyToOne
    @JoinColumn(name = "car")
    private User user;

    public Car() {
    }

    public Car of(String mark, String bodyType) {
        Car car = new Car();
        this.mark = mark;
        this.bodyType = bodyType;
        return car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", mark='" + mark + '\''
                + ", bodyType='" + bodyType + '\''
                + ", user=" + user
                + '}';
    }
}
