INSERT INTO develop.user(id, name, email, password)
values (1, 'Petr Arsentev', 'root@local', 'root')
on conflict do nothing;

INSERT INTO develop.image(id, imagename)
VALUES (1, 'nameFhoto.jpg');

INSERT INTO develop.auto(id, mark, bodytype, engine, transmission, color, drive, mileage)
VALUES (1, 'Kia Sportage', 'suv', 'бензин, 2.0 л.,	150 л.с.,', 'АКПП', 'коричневый', 'передний', '36 500'),
       (2, 'Honda Jazz', 'хэтчбек 5 дв.', 'бензин, 1.3 л 100 л.с.', 'АКПП', 'sky blue', 'передний', '83 000'),
       (3, 'Nissan Juke', 'suv', 'бензин, 1.6 л., 117 л.с.', 'вариатор', 'бордовый', 'передний', '89 000')
on conflict do nothing;

INSERT INTO develop.post(id, header, description, price, status, created, car_id, image_id, user_id)
VALUES (1, 'Kia Sportage, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! сигнализация.',
        '809 000 ₽', 'false', '2021-10-26 19:50:11.853', 1, 1, 1),
       (2, 'Honda Jazz, 2012 год', 'ДИЛЕРСКИЙ АВТОМОБИЛЬ! Новая резина, сигнализация. без вложений',
        '720 000 ₽', 'false', '2021-09-21 17:50:10.853', 1, 1, 1),
       (3, 'Nissan Juke, 2011 год',
        'МАКСИМАЛЬНАЯ КОМПЛЕКТАЦИЯ! ПОЛНЫЙ ПРИВОД! БЕЗ ДТП и окрасов! 2 комплекта резины на дисках, сигнализация',
        '890 000 ₽', 'false', '2021-11-16 14:34:25.853', 1, 1, 1)
on conflict do nothing;

insert into dictionary.transmission()