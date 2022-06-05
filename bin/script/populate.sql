INSERT INTO  user (name, password, role) values ( 'admin1' ,'1234', 'ADMIN');
INSERT INTO  user (name, password, role) values ( 'user1' ,'5678', 'USER');


INSERT INTO  materiel (name, code, stock, disponibilite) values ( 'LIVRE' ,'LI', 5, true);
INSERT INTO  materiel (name, code, stock, disponibilite) values ( 'CHAISE' ,'CH',5, true);

INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'LIVRE' ,'lll1', 5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'CHAISE' ,'ccc1',5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'LIVRE' ,'lll2', 5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'CHAISE' ,'ccc2',5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'LIVRE' ,'lll3', 5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'CHAISE' ,'ccc3',5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'LIVRE' ,'lll4', 5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'CHAISE' ,'ccc4',5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'LIVRE' ,'lll5', 5, true);
INSERT INTO  materiel(name, code, stock, disponibilite) values ( 'CHAISE' ,'ccc5',5, true);
 
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (1, 1, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (2, 1, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (3, 2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (4, 2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (5, 1, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (6, 1, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (7, 1, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (8, 2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (9, 2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (10,2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (11,2, '2022-06-05 09:51:41.866');
INSERT INTO  allocation (idMateriel, idUser, dateAllocation) values (12,2, '2022-06-05 09:51:41.866');