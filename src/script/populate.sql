INSERT INTO  users (name, password, role) values ( 'admin' ,'21232f297a57a5a743894a0e4a801fc3' ,'admin');
INSERT INTO  users (name, password, role) values ( 'user1' ,'b75705d7e35e7014521a46b532236ec3' ,'user');
INSERT INTO  users (name, password, role) values ( 'user2' ,'8bd108c8a01a892d129c52484ef97a0d' ,'user');
//INSERT INTO  users (name, password, role) values ( 'admin' ,'admin' ,'admin');
INSERT INTO  materiel(name, code, stock,disponibility ) values ( 'LA boite à merveille' ,'LI' ,5 ,true);
INSERT INTO  materiel(name, code, stock,disponibility ) values ( 'Chaise1' ,'CH' ,2 ,true);
INSERT INTO  materiel(name, code, stock,disponibility ) values ( 'Candide' ,'LI' ,10 ,true);
INSERT INTO  materiel(name, code, stock,disponibility ) values ( 'Chaise2' ,'CH' ,7 ,true);
INSERT INTO  Allocation(userid, materialid, date ) values ( 1 ,2 ,'2022-05-09');
INSERT INTO  Allocation(userid, materialid, date ) values ( 1 ,3 ,'2022-05-19');
INSERT INTO  Allocation(userid, materialid, date ) values ( 2 ,1 ,'2022-06-06');
INSERT INTO  Allocation(userid, materialid, date ) values ( 2 ,3 ,'2022-05-29');









