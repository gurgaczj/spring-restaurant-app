USE restaurant_users;

INSERT INTO restaurant_users.role VALUES (NULL, 'admin');
INSERT INTO restaurant_users.role VALUES (NULL, 'user');

INSERT INTO restaurant_users.address VALUES (NULL, '3-go Maja', 13, NULL, NULL, '34-532', 'Kraków');
INSERT INTO restaurant_users.address VALUES (NULL, 'Ulicowata', 15, 10, 2, '12-452', 'Warszawa');
INSERT INTO restaurant_users.address VALUES (NULL, 'Drogowa', 65, 54, 12, '45-234', 'Rzeszów');

INSERT INTO restaurant_users.user_info VALUES (NULL, 'Adam', 'Adamowski', 123456789, 1);
INSERT INTO restaurant_users.user_info VALUES (NULL, 'Jan', 'Janowski', 987654321, 2);
INSERT INTO restaurant_users.user_info VALUES (NULL, 'Paweł', 'Pawłowski', 648532579, 3);

INSERT INTO restaurant_users.registration_info VALUES (NULL, now(), 'dsfsadgsdags', now(1));
INSERT INTO restaurant_users.registration_info VALUES (NULL, now(), 'sjgfghstr', now(6));
INSERT INTO restaurant_users.registration_info VALUES (NULL, now(), 'dsfsge5g4g54adgsdags', NULL);

INSERT INTO restaurant_users.user VALUES (NULL, 'adam', '123', 'adam@mail.com', true, true, 1, 1, 1);
INSERT INTO restaurant_users.user VALUES (NULL, 'jan', '123', 'jan@mail.com', true, true, 2, 2, 2);
INSERT INTO restaurant_users.user VALUES (NULL, 'pawel', '123', 'pawel@mail.com', false, false, 2, 3, 3);