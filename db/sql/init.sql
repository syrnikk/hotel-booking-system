-- create database and user

-- DROP DATABASE IF EXISTS hotel_db;
-- CREATE DATABASE hotel_db;

-- DROP USER IF EXISTS hotel_user;
-- CREATE USER hotel_user WITH ENCRYPTED PASSWORD 'hoteluserpassword';
-- GRANT ALL PRIVILEGES ON DATABASE hotel_db TO hotel_user;


-- create table

DROP TABLE IF EXISTS kraj CASCADE;
DROP TABLE IF EXISTS miasto CASCADE;
DROP TABLE IF EXISTS adres CASCADE;
DROP TABLE IF EXISTS rola CASCADE;
DROP TABLE IF EXISTS uzytkownik CASCADE;
DROP TABLE IF EXISTS uzytkownik_rola CASCADE;
DROP TABLE IF EXISTS rezerwacja CASCADE;
DROP TABLE IF EXISTS typ_pokoju CASCADE;
DROP TABLE IF EXISTS hotel CASCADE;
DROP TABLE IF EXISTS pokoj CASCADE;
DROP TABLE IF EXISTS pokoj_rezerwacja CASCADE;
DROP TABLE IF EXISTS ocena CASCADE;

CREATE TABLE kraj(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    nazwa           VARCHAR(64) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE miasto(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    kraj_id         BIGINT NOT NULL,
    nazwa           VARCHAR(64) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(kraj_id) REFERENCES kraj(id)
);

CREATE TABLE adres(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    miasto_id       BIGINT NOT NULL,
    ulica           VARCHAR(64) NOT NULL,
    numer           VARCHAR(16) NOT NULL,
    kod_pocztowy    VARCHAR(16) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(miasto_id) REFERENCES miasto(id)
);

CREATE TABLE rola(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    nazwa           VARCHAR(64) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE uzytkownik(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    imie            VARCHAR(128) NOT NULL,
    nazwisko        VARCHAR(128) NOT NULL,
    email           VARCHAR(128) NOT NULL UNIQUE,
    haslo           VARCHAR(255) NOT NULL,
    telefon         VARCHAR(16),
    aktywny         BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY(id)
);

CREATE TABLE uzytkownik_rola(
    uzytkownik_id   BIGINT,
    rola_id         BIGINT,
    PRIMARY KEY(uzytkownik_id, rola_id),
    FOREIGN KEY(uzytkownik_id) REFERENCES uzytkownik(id),
    FOREIGN KEY(rola_id) REFERENCES rola(id)
);

CREATE TABLE rezerwacja(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    uzytkownik_id   BIGINT NOT NULL,
    przyjazd_data   DATE NOT NULL,
    odjazd_data     DATE NOT NULL,
    komentarz       TEXT,
    PRIMARY KEY(id),
    FOREIGN KEY(uzytkownik_id) REFERENCES uzytkownik(id)
);

CREATE TABLE typ_pokoju(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    typ             VARCHAR(128) NOT NULL,
    ilosc_osob      INT NOT NULL,
    cena            DECIMAL NOT NULL,
    opis            TEXT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE hotel
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    nazwa           VARCHAR(128) NOT NULL,
    adres_id        BIGINT NOT NULL UNIQUE,
    telefon         VARCHAR(16) NOT NULL,
    tytul           VARCHAR(255) NOT NULL,
    opis            TEXT NOT NULL,
    gwiazdki        INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(adres_id) REFERENCES adres(id)
);


CREATE TABLE pokoj(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    typ_pokoju_id   BIGINT NOT NULL,
    hotel_id        BIGINT NOT NULL,
    numer_pokoju    VARCHAR(8) NOT NULL,
    pietro          INT,
    PRIMARY KEY(id),
    FOREIGN KEY(hotel_id) REFERENCES hotel(id),
    FOREIGN KEY(typ_pokoju_id) REFERENCES typ_pokoju(id)
);

CREATE TABLE pokoj_rezerwacja(
    pokoj_id        BIGINT NOT NULL,
    rezerwacja_id   BIGINT NOT NULL,
    PRIMARY KEY(pokoj_id, rezerwacja_id),
    FOREIGN KEY(pokoj_id) REFERENCES pokoj(id),
    FOREIGN KEY(rezerwacja_id) REFERENCES rezerwacja(id)
);

CREATE TABLE ocena
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    ocena           DECIMAL(1,1) NOT NULL,
    uzytkownik_id   BIGINT NOT NULL,
    hotel_id        BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(hotel_id) REFERENCES hotel(id),
    FOREIGN KEY(uzytkownik_id) REFERENCES uzytkownik(id)
);