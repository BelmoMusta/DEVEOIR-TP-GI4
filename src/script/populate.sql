
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Livre' ,'LI', true, 35);
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Chaise' ,'CH', true, 50 );

INSERT INTO users(username, password, role) values ('emp', '2714379c50bf21b94ac61a5a5456d02a4be96cc3', 'emp');
INSERT INTO users(username, password, role) values ('admin','d033e22ae348aeb5660fc2140aec35850c4da997', 'admin');
INSERT INTO users(username, password, role) values ('super','8451ba8a14d79753d34cb33b51ba46b4b025eb81', 'admin , emp');

INSERT INTO materielAllocated(userID, materielID, quantity) VALUES (2, 1, 4);
INSERT INTO materielAllocated(userID, materielID, quantity) VALUES (1, 1, 8);


-- Pour s'authentifier à l'application :

-- username = emp   / password = emp   : pour un utilsateur normal
-- username = admin / password = admin : pour un administrateur
-- username = super / password = super : pour un utilsateur super (les deux rôles)