/*Таблица к 0. Что такое интеграционное тестирование. [#6875]
 * Уровень : 3. Мидл Категория : 3.3. Hibernate Топик : 3.3.4. Интеграционное тестирование */
CREATE TABLE orders
(
    id          serial,
    name        VARCHAR(50),
    description VARCHAR(50),
    created     timestamp,
    PRIMARY KEY (id)
);