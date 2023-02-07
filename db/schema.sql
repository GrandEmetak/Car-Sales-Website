CREATE TABLE users
(
    id       BIGSERIAL NOT NULL,
    email    VARCHAR(255),
    name     VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT users_pk PRIMARY KEY (id)
);
CREATE TABLE cars
(
    id           BIGSERIAL NOT NULL,
    bodyType     VARCHAR(255),
    color        VARCHAR(255),
    drive        VARCHAR(255),
    engine       VARCHAR(255),
    mark         VARCHAR(255),
    mileage      VARCHAR(255),
    transmission VARCHAR(255),
    CONSTRAINT cars_pk PRIMARY KEY (id)
);
CREATE TABLE photos
(
    id        BIGSERIAL NOT NULL,
    imageName VARCHAR(255),
    CONSTRAINT photos_pk PRIMARY KEY (id)
);
CREATE TABLE posts
(
    id          BIGSERIAL NOT NULL,
    created     timestamp,
    description VARCHAR(255),
    header      VARCHAR(255),
    price       VARCHAR(255),
    status      boolean NOT NULL,
    car_id      BIGINT    NOT NULL,
    photo_id    BIGINT    NOT NULL,
    user_id     BIGINT    NOT NULL,
    CONSTRAINT photos_pk PRIMARY KEY (id),
    CONSTRAINT photos_car_id_fk FOREIGN KEY (car_id) REFERENCES cars (id),
    CONSTRAINT photos_photo_id_fk FOREIGN KEY (photo_id) REFERENCES posts (id),
    CONSTRAINT photos_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

