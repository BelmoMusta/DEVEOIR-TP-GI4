
INSERT INTO  user (name, password, role) values ( 'admin1' ,'$2a$10$NHNurgnIT0ILR09nOXX1r./m6GKjGddUODd/oz3VPmDpY1k/Qj7XW', 'ADMIN');
INSERT INTO  user (name, password, role) values ( 'user1' ,'$2a$10$bUY2IsjDBOS3o3sp8csvQOrU4T8dcxO2APFQEb.OfqTa8i06WB.ny', 'USER');


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
