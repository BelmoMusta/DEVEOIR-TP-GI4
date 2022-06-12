
INSERT INTO  materiel(name, code, availability, _available) values ( 'Livre' ,'LI', 100, true  );
INSERT INTO  materiel(name, code, availability, _available) values ( 'Chaise' ,'CH', 100, true );

INSERT INTO  user(username, password, type) values ( 'employee' ,'employee', 'employee' );
INSERT INTO  user(username, password, type) values ( 'admin' ,'admin', 'admin'  );

INSERT INTO allocations(userId, materielId, quantity) values (1, 1, 20);
INSERT INTO allocations(userId, materielId, quantity) values (2, 1, 20);