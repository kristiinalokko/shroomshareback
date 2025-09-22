--  INSERT INTO
-- NB!!!! Ära unusta, et IntelliJ database tööriist võtab kõik primary key väärtused andmeteda
--   aga insert lausetes soovime me kasutada default väärtusi (andmebaas ise annab neid primary key väärtusi).
--   Asenda numbrilised väärtused sõnaga deault)

insert into shroomshare.role(id, name) values (default, 'admin');
insert into shroomshare.role(id, name) values (default, 'user');

insert into shroomshare."user"(id, role_id, username, password, status) VALUES (default, 1, 'admin', 123, 'A' );
insert into shroomshare."user"(id, role_id, username, password, status) VALUES (default, 2, 'user', 123, 'A' );

insert into shroomshare.location(id, user_id, name, latitude, longitude, description, created, last_active, status, avg_rating) VALUES (default, 1, 'Paide seenekoht', 58.885, 25.557, 'Parim seenekoht, väga palju seeni', '2025-09-12', '2025-09-22', 'A', 2);
insert into shroomshare.location(id, user_id, name, latitude, longitude, description, created, last_active, status, avg_rating) VALUES (default, 2, 'Tallinna mets', 58.945, 24.794, 'Koordinaadid vist viskavad linna, mis toimub', '2025-09-13', '2025-09-21', 'A', 4);


