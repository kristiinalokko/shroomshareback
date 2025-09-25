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

-- Lisa testkasutaja
INSERT INTO shroomshare."user" (id, role_id, username, password, status) VALUES
    (default, 1, 'testuser', 'password', 'A');

-- Lisa test asukohad
INSERT INTO shroomshare.location (id, user_id, name, latitude, longitude, description, created, last_active, status, avg_rating) VALUES
                                                                                                                                     (default, 1, 'Mets Tagaaias', 58.3781234, 26.7325678, 'Kaunis mets', CURRENT_DATE, CURRENT_DATE, 'A', 0.0),
                                                                                                                                     (default, 1, 'Sooserv', 58.6954321, 25.1356789, 'Niiske soo', CURRENT_DATE, CURRENT_DATE, 'A', 0.0);
INSERT INTO shroomshare.shroom (id, user_id, name, description, status) VALUES
                                                                            (default, 1, 'Kukeseene', 'Väga maitsev', 'A'),
                                                                            (default, 1, 'Puravik', 'Hea suppides', 'A'),
                                                                            (default, 1, 'Kukeseene', 'Teine koht', 'A'),
                                                                            (default, 1, 'Pilvik', 'Haruldane seen', 'A');

INSERT INTO shroomshare.shroom_location (id, location_id, shroom_id) VALUES
                                                                         (default, 1, 1),  -- Kukeseene mets tagaaias
                                                                         (default, 1, 2),  -- Puravik mets tagaaias
                                                                         (default, 2, 1),  -- Kukeseene sooserv
                                                                         (default, 2, 4);  -- Pilvik sooserv
insert into shroomshare.shroom(id, user_id, name, description, status) VALUES (default,1,'kukeseen', 'nämm nämm', 'A');
insert into shroomshare.shroom(id, user_id, name, description, status) VALUES (default,2,'puravik', 'hea seen', 'A');

insert into shroomshare.shroom_location(id, location_id, shroom_id) VALUES (default, 1, 1);
insert into shroomshare.shroom_location(id, location_id, shroom_id) VALUES (default, 2, 2);