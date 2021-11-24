package ru.job4j.cars.model;

import java.util.Date;
import java.util.List;
import javax.persistence.GenerationType;
import javax.persistence.*;

import java.util.Objects;

/**
 * Модель данных описывающая объявление(Post)
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
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private boolean status;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Photo photo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post() {
    }

    public Post of(String description, boolean status) {
        Post post = new Post();
        post.description = description;
        post.status = false;
        this.created = new Date(System.currentTimeMillis());
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Post: id=%s, description=%s, status=%s, User=%s, Car=%s, created=%s",
                id, description, status, user, car, created);
    }
}
