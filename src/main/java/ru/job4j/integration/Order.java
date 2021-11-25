package ru.job4j.integration;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * 0. Что такое интеграционное тестирование. [#6875]
 * Уровень : 3. Мидл Категория : 3.3. Hibernate Топик : 3.3.4. Интеграционное тестирование
 * Возьмите класс работы с БД из описания package ru.job4j.integration.OrderStore,
 * добавьте еще 2 метода - обновление записи в БД и получение записи из БД по ее имени.
 * - обновление обновление записи в БД
 * - получение записи из БД по ее имени.
 * Напишите тесты на все методы класса OrderStore.
 * @SlartiBartFast-art
 * @since 25.11.21
 */
public class Order {
    private int id;

    private String name;

    private String description;

    private Timestamp created;

    public Order() {
    }

    public Order(int id, String name, String description, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public static Order of(String name, String description) {
        Order o = new Order();
        o.name = name;
        o.description = description;
        o.created = new Timestamp(System.currentTimeMillis());
        return o;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
