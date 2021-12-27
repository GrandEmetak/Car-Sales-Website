CREATE TABLE users (
                       id       serial,
                       email    varchar(255),
                       name     varchar(255),
                       password varchar(255),
                       PRIMARY KEY (id)
);
CREATE TABLE cars (
                      id           serial,
                      bodyType     varchar(255),
                      color        varchar(255),
                      drive        varchar(255),
                      engine       varchar(255),
                      mark         varchar(255),
                      mileage      varchar(255),
                      transmission varchar(255),
                      PRIMARY KEY (id)
);
CREATE TABLE photos (
                        id        serial,
                        imageName varchar(255),
                        PRIMARY KEY (id)
);
CREATE TABLE posts (
                       id          serial,
                       created     timestamp,
                       description varchar(255),
                       header      varchar(255),
                       price       varchar(255),
                       status      boolean not null,
                       car_id      INT     NOT NULL REFERENCES cars (id),
                       photo_id    INT     NOT NULL REFERENCES posts (id),
                       user_id     INT     NOT NULL REFERENCES users (id),
                       PRIMARY KEY (id)
);

