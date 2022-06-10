--
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Livre' ,'LI', true, 50);
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Chaise' ,'CH', true, 60 );

INSERT INTO users(username, password, role) values ('employee', 'empl123', 'employée');
INSERT INTO users(username, password, role) values ('admin','admin123', 'administrateur');
INSERT INTO users(username, password, role) values ('ADMIN','admin123', 'administrateur , employée');

INSERT INTO materielAllocated(userID, materielID, quantity) VALUES (1, 1, 10);
