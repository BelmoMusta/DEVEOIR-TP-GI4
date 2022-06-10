
INSERT INTO  materiel(name, code,disponible) values ( 'Livre' ,'LI',true  );
INSERT INTO  materiel(name, code,disponible) values ( 'Chaise' ,'CH',true  );


INSERT INTO users(username,password) values ('admin','admin');
INSERT INTO users(username,password) values ('user','user');
INSERT INTO users(username,password) values ('','1234');



INSERT INTO roles (role) VALUES ('USER');
INSERT INTO roles (role) VALUES ('ADMIN');

INSERT INTO users_roles (username, role) VALUES ('admin', 'ADMIN');
INSERT INTO users_roles (username, role) VALUES ('user', 'USER');

