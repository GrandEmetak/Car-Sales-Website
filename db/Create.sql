
create table cars
(
    id        serial primary key,
    mark      VARCHAR(255),
    body_type varchar(255),
    user_id   INT NOT NULL REFERENCES users (id)
);

create table posts
(
    id          serial primary key,
    description VARCHAR(2000),
    status      boolean,
    created     TIMESTAMP with time zone,
    user_id     INT NOT NULL REFERENCES users (id),
    car_id      INT NOT NULL REFERENCES cars (id),
    photos_id      INT NOT NULL REFERENCES posts (id)
);

create table photos
(
    id        serial primary key,
    imagename VARCHAR(255),
    post_id   INT NOT NULL REFERENCES posts (id)
);

create table users
(
    id       serial primary key,
    name     VARCHAR(255),
    email    VARCHAR(55),
    password VARCHAR(255),
    /*post_id  INT NOT NULL REFERENCES posts (id),
    car_id   INT NOT NULL REFERENCES cars (id),*/
    unique (email)
);

INSERT INTO posts(description, status, created)
values ('ДИЛЕРСКИЙ АВТОМОБИЛЬ! МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПАНОРАМНАЯ КРЫША! ПОЛНЫЙ ПРИВОД! ГБО ПОСЛЕДНЕГО ПОКОЛЕНИЯ! Новая резина, сигнализация. без вложений',
        'false', '2021-10-26 19:50:11.853'),
       ('ДИЛЕРСКИЙ АВТОМОБИЛЬ! МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ЛЮК В КРЫШЕ! ПОЛНЫЙ ПРИВОД! ДИЗЕЛЬ! Новая резина, сигнализация. без вложений',
        'false', '2021-09-21 17:50:10.853'),
       ('МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! Новая резина, сигнализация. без вложений', 'false',
        '2021-11-16 14:34:25.853');

insert into users(name, email, password)
values ('Petr Arsentev', 'PetrArsentev@mail.ru', 'root@tool');