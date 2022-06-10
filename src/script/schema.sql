DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS materials;
DROP TABLE IF EXISTS allocations;

CREATE TABLE roles (
                       role_id identity NOT NULL PRIMARY KEY,
                       name varchar
);

CREATE TABLE users (
                       user_id identity NOT NULL PRIMARY KEY ,
                       username varchar,
                       hashed_password varchar,
                       role_id integer,
                       foreign key (role_id) references roles(role_id)
);

CREATE TABLE materials (
                           material_id identity NOT NULL PRIMARY KEY,
                           name varchar,
                           quantity integer,
                           material_type varchar

);

CREATE TABLE allocations (
                             allocation_id identity NOT NULL PRIMARY KEY,
                             user_id integer,
                             material_id integer,
                             duration integer,
                             foreign key (material_id) references materials(material_id),
                             foreign key (user_id) references users(user_id)
);




