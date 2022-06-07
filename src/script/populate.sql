
INSERT INTO  materiel(name, code, type, stock, dispo) values ( 'Livre JavaScript' ,'LI', 'LIVRE' , 7, true );
INSERT INTO  materiel(name, code, type, stock, dispo) values ( 'Livre JAVA' ,'LI', 'LIVRE' , 44, true );



INSERT INTO  user(id, username, password, role) VALUES ( 1,'aymane' ,'aymane0101','admin');
INSERT INTO  user(id, username, password, role) VALUES ( 2,'nisrine','nisrine0101','user' );


INSERT INTO allocation(idUser, idMat, allocationDuration) VALUES (1, 2, '2022-06-07 17:49:18.937');
INSERT INTO allocation(idUser, idMat, allocationDuration) VALUES (1, 1, '2022-06-07 17:50:09.900');
INSERT INTO allocation(idUser, idMat, allocationDuration) VALUES (2, 2, '2022-06-07 19:29:8.987');


