--
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Livre' ,'LI', true, 50);
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Chaise' ,'CH', true, 60 );

INSERT INTO users(username, password, role) values ('emp', '2714379c50bf21b94ac61a5a5456d02a4be96cc3', 'employée');
INSERT INTO users(username, password, role) values ('admin','d033e22ae348aeb5660fc2140aec35850c4da997', 'administrateur');
INSERT INTO users(username, password, role) values ('ADMIN','d033e22ae348aeb5660fc2140aec35850c4da997', 'administrateur , employée');

INSERT INTO materielAllocated(userID, materielID, quantity) VALUES (1, 1, 10);
