package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
public class OrderStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrderStore store = new OrderStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndThenUpdateOrder() {
        OrderStore store = new OrderStore(pool);

        var one = store.save(Order.of("name1", "description1"));

        var two = new Order(one.getId(), "Ivan", "description2", one.getCreated());

        Order all = store.updateByOrder(two);
        assertThat(all.getId(), is(1));
        assertThat(all.getName(), is("Ivan"));
        assertThat(all.getDescription(), is("description2"));
    }

    @Test
    public void whenSaveOrderAndGetAllOrderByName() {
        OrderStore store = new OrderStore(pool);

        store.save(Order.of("Ivan Ars", "description1"));
        String nameToFind = "Ivan Ars";

        List<Order> all = (List<Order>) store.findAllOrderByName(nameToFind);
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));

    }
}