DROP DATABASE restaurant_users;
CREATE DATABASE restaurant_users;
USE restaurant_users;

CREATE TABLE role
(
    id       INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    rolename enum ('USER', 'ADMIN')
);

CREATE TABLE address
(
    id               INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    street           VARCHAR(128)      NOT NULL,
    house_number SMALLINT UNSIGNED NOT NULL,
    apartment_number SMALLINT UNSIGNED DEFAULT NULL,
    flat_number      TINYINT UNSIGNED DEFAULT NULL,
    postal_code      VARCHAR(6)        NOT NULL,
    city             VARCHAR(128)      NOT NULL
) CHARACTER SET utf8
  COLLATE utf8_polish_ci;

CREATE TABLE user_info
(
    id           INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(128) NOT NULL,
    last_name    VARCHAR(128) NOT NULL,
    phone_number INT UNSIGNED NOT NULL,
    address_id   INT UNSIGNED NOT NULL,
    FOREIGN KEY (address_id) REFERENCES address (id)
) CHARACTER SET utf8
  COLLATE utf8_polish_ci;

CREATE TABLE registration_info
(
    id                INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    registration_date DATETIME NOT NULL,
    hash              VARCHAR(64),
    activation_date   DATETIME DEFAULT NULL
);

CREATE TABLE user
(
    id                   INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username             VARCHAR(64)        NOT NULL,
    password             VARCHAR(64)        NOT NULL,
    email                VARCHAR(250)       NOT NULL,
    is_activated         BOOL DEFAULT FALSE NOT NULL,
    is_enabled           BOOL DEFAULT FALSE NOT NULL,
    role_id              INT UNSIGNED       NOT NULL,
    registration_info_id INT UNSIGNED       NOT NULL,
    user_info_id         INT UNSIGNED       NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (registration_info_id) REFERENCES registration_info (id),
    FOREIGN KEY (user_info_id) REFERENCES user_info (id)
);