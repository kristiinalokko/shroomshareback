-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-09-18 07:21:44.866

-- tables
-- Table: comment
CREATE TABLE comment
(
    id          serial       NOT NULL,
    location_id int          NOT NULL,
    user_id     int          NOT NULL,
    body        varchar(255) NULL,
    rating      int          NOT NULL,
    created     date         NOT NULL,
    status      char(1)      NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (id)
);

-- Table: comment_image
CREATE TABLE comment_image
(
    id         serial NOT NULL,
    comment_id int    NOT NULL,
    image_data bytea  NOT NULL,
    CONSTRAINT comment_image_pk PRIMARY KEY (id)
);

-- Table: favorites
CREATE TABLE favorites
(
    id          serial NOT NULL,
    user_id     int    NOT NULL,
    location_id int    NOT NULL,
    CONSTRAINT favorites_pk PRIMARY KEY (id)
);

-- Table: inbox
CREATE TABLE inbox
(
    id               serial       NOT NULL,
    receiver_user_id int          NOT NULL,
    sender_user_id   int          NOT NULL,
    title            varchar(100) NOT NULL,
    message          varchar(500) NOT NULL,
    status           varchar(3)   NOT NULL,
    location_id      int          NULL,
    CONSTRAINT inbox_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location
(
    id          serial         NOT NULL,
    user_id     int            NOT NULL,
    name        varchar(255)   NOT NULL,
    latitude    decimal(10, 7) NOT NULL,
    longitude   decimal(10, 7) NOT NULL,
    description varchar(255)   NULL,
    created     date           NOT NULL,
    last_active date           NOT NULL,
    status      char(1)        NOT NULL,
    avg_rating  decimal(2, 1)  NOT NULL,
    CONSTRAINT location_pk PRIMARY KEY (id)
);

-- Table: location_image
CREATE TABLE location_image
(
    id          serial NOT NULL,
    location_id int    NOT NULL,
    image_data  bytea  NOT NULL,
    CONSTRAINT location_image_pk PRIMARY KEY (id)
);

-- Table: user_image
CREATE TABLE user_image
(
    id         serial NOT NULL,
    user_id    int    NOT NULL,
    image_data bytea  NOT NULL,
    CONSTRAINT user_image_pk PRIMARY KEY (id)
);


-- Table: profile
CREATE TABLE profile
(
    id          serial       NOT NULL,
    user_id     int          NOT NULL,
    first_name  varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    email       varchar(255) NOT NULL,
    description varchar(255) NULL,
    CONSTRAINT profile_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role
(
    id   serial      NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: shroom
CREATE TABLE shroom
(
    id          serial       NOT NULL,
    user_id     int          NOT NULL,
    name        varchar(255) NOT NULL,
    description varchar(255) NULL,
    status      char(1)      NOT NULL,
    CONSTRAINT shroom_pk PRIMARY KEY (id)
);

-- Table: shroom_image
CREATE TABLE shroom_image
(
    id         serial NOT NULL,
    shroom_id  int    NOT NULL,
    image_data bytea  NOT NULL,
    CONSTRAINT shroom_image_pk PRIMARY KEY (id)
);

-- Table: shroom_location
CREATE TABLE shroom_location
(
    id          serial NOT NULL,
    location_id int    NOT NULL,
    shroom_id   int    NOT NULL,
    CONSTRAINT shroom_location_pk PRIMARY KEY (id)
);

-- Table: time_range
CREATE TABLE time_range
(
    id   serial      NOT NULL,
    name varchar(10) NOT NULL,
    days int         NOT NULL,
    CONSTRAINT time_range_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user"
(
    id       serial      NOT NULL,
    role_id  int         NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    status   char(1)     NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: comment_image_comment (table: comment_image)
ALTER TABLE comment_image
    ADD CONSTRAINT comment_image_comment
        FOREIGN KEY (comment_id)
            REFERENCES comment (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: comment_location (table: comment)
ALTER TABLE comment
    ADD CONSTRAINT comment_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: comment_user (table: comment)
ALTER TABLE comment
    ADD CONSTRAINT comment_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: favorites_location (table: favorites)
ALTER TABLE favorites
    ADD CONSTRAINT favorites_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: favorites_user (table: favorites)
ALTER TABLE favorites
    ADD CONSTRAINT favorites_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: inbox_user_receiver (table: inbox)
ALTER TABLE inbox
    ADD CONSTRAINT inbox_user_receiver
        FOREIGN KEY (receiver_user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: inbox_user_sender (table: inbox)
ALTER TABLE inbox
    ADD CONSTRAINT inbox_user_sender
        FOREIGN KEY (sender_user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: location_image_location (table: location_image)
ALTER TABLE location_image
    ADD CONSTRAINT location_image_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: location_user (table: location)
ALTER TABLE location
    ADD CONSTRAINT location_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: profile_user (table: profile)
ALTER TABLE profile
    ADD CONSTRAINT profile_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: shroom_image_shroom (table: shroom_image)
ALTER TABLE shroom_image
    ADD CONSTRAINT shroom_image_shroom
        FOREIGN KEY (shroom_id)
            REFERENCES shroom (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: shroom_location_location (table: shroom_location)
ALTER TABLE shroom_location
    ADD CONSTRAINT shroom_location_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: shroom_location_shroom (table: shroom_location)
ALTER TABLE shroom_location
    ADD CONSTRAINT shroom_location_shroom
        FOREIGN KEY (shroom_id)
            REFERENCES shroom (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: shroom_user (table: shroom)
ALTER TABLE shroom
    ADD CONSTRAINT shroom_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user"
    ADD CONSTRAINT user_role
        FOREIGN KEY (role_id)
            REFERENCES role (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

ALTER TABLE user_image
    ADD CONSTRAINT user_image_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;
-- End of file.

