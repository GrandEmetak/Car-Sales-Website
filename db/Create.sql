create table users
(
    id       serial primary key,
    email    varchar(255),
    name     varchar(255),
    password varchar(255)
);

create table cars
(
    id           serial primary key,
    bodyType     varchar(255),
    color        varchar(255),
    drive        varchar(255),
    engine       varchar(255),
    mark         varchar(255),
    mileage      varchar(255),
    transmission varchar(255)
);
create table photos
(
    id        serial primary key,
    imageName varchar(255)
);

create table posts
(
    id          serial primary key,
    created     timestamp,
    description varchar(255),
    header      varchar(255),
    price       varchar(255),
    status      boolean not null,
    car_id      INT     NOT NULL REFERENCES cars (id),
    photo_id    INT     NOT NULL REFERENCES posts (id),
    user_id     INT     NOT NULL REFERENCES users (id)
);

insert into users(name, email, password)
values ('Petr Arsentev', 'root@local', 'root');

insert into cars(mark, bodytype, engine, transmission, color, drive, mileage)
values ('Kia Sportage', 'suv', 'бензин, 2.0 л.,	150 л.с.,', 'АКПП', 'коричневый', 'передний', '36 500');
insert into cars(mark, bodytype, engine, transmission, color, drive, mileage)
values ('Honda Jazz', 'хэтчбек 5 дв.', 'бензин, 1.3 л 100 л.с.', 'АКПП', 'sky blue', 'передний', '83 000');
insert into cars(mark, bodytype, engine, transmission, color, drive, mileage)
values ('Nissan Juke', 'suv', 'бензин, 1.6 л., 117 л.с.', 'вариатор', 'бордовый', 'передний', '89 000');

INSERT INTO posts(header, description, price, status, created, car_id, photo_id, user_id)
values ('Kia Sportage, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! сигнализация.',
        '809 000 ₽', 'false', '2021-10-26 19:50:11.853', 1, 1, 1);
INSERT INTO posts(header, description, price, status, created, car_id, photo_id, user_id)
values ('Honda Jazz, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! Новая резина, сигнализация. без вложений',
        '720 000 ₽', 'false', '2021-09-21 17:50:10.853', 1, 1, 1);
INSERT INTO posts(header, description, price, status, created, car_id, photo_id, user_id)
values ('Nissan Juke, 2011 год',
        'МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! БЕЗ ДТП и окрасов! 2 комплекта резины на дисках, сигнализация',
        '890 000 ₽', 'false', '2021-11-16 14:34:25.853', 1, 1, 1);
INSERT INTO photos(imagename)
values ('nameFhoto.jpg');

