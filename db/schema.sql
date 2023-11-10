drop table if exists develop.user cascade;

drop table if exists develop.auto cascade;

drop table if exists develop.image cascade;

DROP SEQUENCE IF EXISTS develop.hibernate_sequence;

CREATE SEQUENCE IF NOT EXISTS develop.hibernate_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;

drop table if exists dictionary.transmission cascade;

DROP SEQUENCE IF EXISTS dictionary.hibernate_sequence;

CREATE SEQUENCE IF NOT EXISTS dictionary.hibernate_sequence AS bigint START WITH 1000 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;

CREATE TABLE develop.user
(
    id       BIGSERIAL NOT NULL,
    email    VARCHAR(255),
    name     VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE develop.auto
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

CREATE TABLE develop.image
(
    id        BIGSERIAL NOT NULL,
    imageName VARCHAR(255),
    CONSTRAINT photos_pk PRIMARY KEY (id)
);

CREATE TABLE develop.post
(
    id          INTEGER NOT NULL,
    header      VARCHAR(255),
    description VARCHAR(255),
    price       VARCHAR(255),
    status      BOOLEAN,
    user_id     INTEGER,
    car_id      INTEGER,
    image_id    INTEGER,
    created     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

ALTER TABLE develop.post
    ADD CONSTRAINT FK_POST_ON_CAR FOREIGN KEY (car_id) REFERENCES develop.auto (id);

ALTER TABLE develop.post
    ADD CONSTRAINT FK_POST_ON_PHOTO FOREIGN KEY (image_id) REFERENCES develop.image (id);

ALTER TABLE develop.post
    ADD CONSTRAINT FK_POST_ON_USER FOREIGN KEY (user_id) REFERENCES develop."user" (id);

CREATE TABLE dictionary.transmission
(
    id           BIGINT NOT NULL,
    name         VARCHAR(255),
    is_archive   BOOLEAN,
    is_read_only BOOLEAN,
    CONSTRAINT pk_transmission PRIMARY KEY (id)
);