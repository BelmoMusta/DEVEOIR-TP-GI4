
INSERT INTO  materiel(name, type, isAvailable, code) values ( 'Livre1' ,'livre', FALSE, '111'  );
INSERT INTO  materiel(name, type, isAvailable, code) values ( 'Livre2' ,'livre', TRUE, '444'  );
INSERT INTO  materiel(name, type, isAvailable, code) values ( 'Chaise1' ,'chaise', TRUE, '222'  );
INSERT INTO  materiel(name, type, isAvailable, code) values ( 'chaise4' ,'chaise', TRUE, '333'  );

INSERT INTO utilisateur(username, password, role) values ('oda', '1234', 'administrateur');
INSERT INTO utilisateur(username, password, role) values ('togashi', '1234', 'employee');
INSERT INTO ALLOCATION(idUser, code, temps) values (1, '111', '2')

