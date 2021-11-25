package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 0. Что такое интеграционное тестирование. [#6875]
 * Уровень : 3. Мидл Категория : 3.3. Hibernate Топик : 3.3.4. Интеграционное тестирование
 * Возьмите класс работы с БД из описания package ru.job4j.integration.OrderStore,
 * добавьте еще 2 метода - обновление записи в БД и получение записи из БД по ее имени.
 * - обновление обновление записи в БД
 * - получение записи из БД по ее имени.
 * Напишите тесты на все методы класса OrderStore.
 *
 * @SlartiBartFast-art
 * @since 25.11.21
 */
public class OrderStore {

    private final BasicDataSource pool;

    public OrderStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Order save(Order order) {
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "INSERT INTO orders(name, description, created) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, order.getName());
            pr.setString(2, order.getDescription());
            pr.setTimestamp(3, order.getCreated());
            pr.execute();
            ResultSet id = pr.getGeneratedKeys();
            if (id.next()) {
                order.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Collection<Order> findAll() {
        List<Order> rsl = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT * FROM orders")) {
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    rsl.add(
                            new Order(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp(4)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Order findById(int id) {
        Order rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                rsl = new Order(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * - обновление обновление записи в БД
     *
     * @param order
     * @return
     */
    public Order updateByOrder(Order order) {
        Order rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("update orders set name = ?, "
                     + "description = ?, created = ? WHERE id = ?")) {
            pr.setString(1, order.getName());
            pr.setString(2, order.getDescription());
            pr.setTimestamp(3, order.getCreated());
            pr.setInt(4, order.getId());
            pr.executeUpdate();
            rsl = order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * -получение записи из БД по ее имени.
     *
     * @param name
     * @return
     */
    public Collection<Order> findAllOrderByName(String name) {
        List<Order> orderList = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("select * from orders WHERE name = ?")) {
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                orderList.add(new Order(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getTimestamp(4)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
