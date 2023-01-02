INSERT INTO rola(id, nazwa) 
VALUES 
(1, 'ROLE_ADMIN'), 
(2, 'ROLE_USER');

INSERT INTO uzytkownik(id, imie, nazwisko, email, haslo, telefon, aktywny) 
VALUES 
(1, 'admin', 'admin', 'admin@gmail.com', '$2a$10$yZQwct033FoSATYIAuiI9eFL1ZXqCa6lo7uEhulEG40JlquGsxTcu','938120912', true),
(2, 'user', 'user', 'user@gmail.com', '$2a$10$yZQwct033FoSATYIAuiI9eFL1ZXqCa6lo7uEhulEG40JlquGsxTcu','938120912', true);

INSERT INTO uzytkownik_rola(uzytkownik_id, rola_id)
VALUES 
(1, 1),
(2, 2);

INSERT INTO kraj(id, nazwa) 
VALUES
(1, 'Hiszpania'),
(2, 'Polska'),
(3, 'Niemcy');

INSERT INTO miasto(id, kraj_id, nazwa)
VALUES
(1, 1, 'Barcelona'),
(2, 1, 'Madryt'),
(3, 1, 'Sewilla'),
(4, 2, 'Kraków'),
(5, 2, 'Warszawa'),
(6, 2, 'Wrocław'),
(7, 3, 'Monachium'),
(8, 3, 'Berlin'),
(9, 3, 'Frankfurt');

INSERT INTO adres(id, miasto_id, ulica, numer, kod_pocztowy)
VALUES
(1, 1, 'ul. BarcelonaStreet', 102, '23-644'),
(2, 1, 'ul. OtherStreet', 125, '23-644'),
(3, 1, 'ul. SomewhereStreet', 296, '23-644');

INSERT INTO hotel(id, nazwa, adres_id, telefon, tytul, opis, gwiazdki)
VALUES
(1, 'Pierwszy Hotel', 1, '468642334', 'Tytul pierwszy', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce feugiat nibh pretium scelerisque viverra. Nunc cursus malesuada tincidunt. Integer eget tincidunt tortor, a molestie erat. Vivamus sit amet felis ac metus venenatis viverra. Vivamus vitae velit et diam dapibus facilisis sed nec arcu. Fusce placerat consectetur magna in interdum.', 4),
(2, 'Drugi Hotel', 2, '468642334', 'Tytul drugi', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce feugiat nibh pretium scelerisque viverra. Nunc cursus malesuada tincidunt. Integer eget tincidunt tortor, a molestie erat. Vivamus sit amet felis ac metus venenatis viverra. Vivamus vitae velit et diam dapibus facilisis sed nec arcu. Fusce placerat consectetur magna in interdum.', 5),
(3, 'Trzeci Hotel', 3, '468642334', 'Tytul Trzeci', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce feugiat nibh pretium scelerisque viverra. Nunc cursus malesuada tincidunt. Integer eget tincidunt tortor, a molestie erat. Vivamus sit amet felis ac metus venenatis viverra. Vivamus vitae velit et diam dapibus facilisis sed nec arcu. Fusce placerat consectetur magna in interdum.', 3);