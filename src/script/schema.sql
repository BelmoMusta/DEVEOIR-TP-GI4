drop table if exists MATERIEL;
drop table if exists  users;
drop table if exists usermateriel;

create table MATERIEL
(
    NAME           CHARACTER VARYING not null,
    CODE           CHARACTER VARYING not null,
    "idMaterial"   INTEGER auto_increment,
    "typeMaterial" CHARACTER VARYING not null,
    "isDisponible" BOOLEAN           not null,
    "isAllouer"    BOOLEAN           not null,
    constraint MATERIEL_PK
        primary key ("CODE")

);


create table users
(
    "idUser"   int auto_increment,
    "userName" varchar not null,
    password   varchar not null,
    role       varchar not null,
    constraint USER_PK
        primary key ("idUser")
);


create table "userMateriel"
(
    "idUserMaterial" int auto_increment,
    "idUser"         int     not null,
    code             varchar not null,
    constraint USERMATERIEL_PK
        primary key ("idUserMaterial"),
    constraint USERMATERIEL_USERS_ID_FK
        foreign key ("idUser") references USERS ("idUser"),
    constraint USERMATERIEL_MATERIEL_ID_FK
        foreign key (code) references MATERIEL(code)

);




