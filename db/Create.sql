/*BD cars*/
create table cars
(
    id           serial primary key,
    mark         VARCHAR(255),
    bodytype     varchar(255),
    engine       varchar(255),
    transmission varchar(255),
    color        varchar(255),
    drive        varchar(255),
    mileage      varchar(255),
    user_id      INT NOT NULL REFERENCES users (id)
);

create table posts
(
    id          serial primary key,
    header      VARCHAR(255),
    description VARCHAR(2000),
    price       VARCHAR(255),
    status      boolean,
    created     TIMESTAMP with time zone,
    user_id     INT NOT NULL REFERENCES users (id),
    car_id      INT NOT NULL REFERENCES cars (id),
    photos_id   INT NOT NULL REFERENCES posts (id)
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
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(55)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    /*post_id  INT NOT NULL REFERENCES posts (id),
    car_id   INT NOT NULL REFERENCES cars (id),*/
    unique (email)
);

insert into users(name, email, password)
values ('Petr Arsentev', 'root@local', 'root');

insert into cars(mark, bodytype, engine, transmission, color, drive, mileage, user_id)
values ('Kia Sportage', 'suv', 'бензин, 2.0 л.,	150 л.с.,', 'АКПП', 'коричневый', 'передний', '36 500', 1);
insert into cars(mark, bodytype, engine, transmission, color, drive, mileage, user_id)
values ('Honda Jazz', 'хэтчбек 5 дв.', 'бензин, 1.3 л 100 л.с.', 'АКПП', 'sky blue', 'передний', '83 000', 1);
insert into cars(mark, bodytype, engine, transmission, color, drive, mileage, user_id)
values ('Nissan Juke', 'suv', 'бензин, 1.6 л., 117 л.с.', 'вариатор', 'бордовый', 'передний', '89 000', 1);

INSERT INTO posts(header, description, price, status, created, user_id, car_id, photos_id)
values ('Kia Sportage, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! сигнализация.',
        '809 000 ₽', 'false', '2021-10-26 19:50:11.853', 1, 1 ,1);
INSERT INTO posts(header, description, price, status, created, user_id, car_id, photos_id)
values ('Honda Jazz, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! Новая резина, сигнализация. без вложений',
        '720 000 ₽', 'false', '2021-09-21 17:50:10.853', 1, 1, 1);
INSERT INTO posts(header, description, price, status, created, user_id, car_id, photos_id)
values ('Nissan Juke, 2011 год',
        'МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! БЕЗ ДТП и окрасов! 2 комплекта резины на дисках, сигнализация',
        '890 000 ₽', 'false', '2021-11-16 14:34:25.853', 1, 1, 1);
INSERT INTO photos(imagename, post_id) values('nameFhoto.jpg', 1)