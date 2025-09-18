--  INSERT INTO
-- NB!!!! Ära unusta, et IntelliJ database tööriist võtab kõik primary key väärtused andmeteda
--   aga insert lausetes soovime me kasutada default väärtusi (andmebaas ise annab neid primary key väärtusi).
--   Asenda numbrilised väärtused sõnaga deault)

insert into shroomshare.role(id, name) values (default, 'admin');
insert into shroomshare.role(id, name) values (default, 'user');

insert into shroomshare."user"(id, role_id, username, password, status) VALUES (default, 1, 'admin', 123, 'A' );
insert into shroomshare."user"(id, role_id, username, password, status) VALUES (default, 2, 'user', 123, 'A' );