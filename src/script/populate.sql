
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'LI',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Chaise' ,'CH',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'HJ',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'NN',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'KK',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'SS',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'HH',true  );
INSERT INTO  materiel(name, code,dispo) values ( 'Livre' ,'FF',true  );

INSERT INTO  materiel(name, code,dispo) values ( 'Chaise' ,'LL',true  );



INSERT INTO users(username,password) values ('hajar','1234');
INSERT INTO users(username,password) values ('noura','1234');
INSERT INTO users(username,password) values ('hanan','1234');


INSERT INTO roles (role) VALUES ('USER');
INSERT INTO roles (role) VALUES ('ADMIN');

INSERT INTO users_roles (username, role) VALUES ('hajar', 'ADMIN');
INSERT INTO users_roles (username, role) VALUES ('noura', 'USER');
INSERT INTO users_roles (username, role) VALUES ('hanan', 'USER');

