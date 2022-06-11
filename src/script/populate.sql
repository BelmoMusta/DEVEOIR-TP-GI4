INSERT INTO  livre(name, code, stock, disponibilite) values ( 'livre' ,'LI', 5, true  );
INSERT INTO  livre(name, code, stock, disponibilite) values ( 'le jour où jai appris à vivre' ,'LI', 5, true  );
INSERT INTO  livre(name, code, stock, disponibilite) values ( 'les demons' ,'LI', 5, false  );
INSERT INTO  chaise(name, code, stock, disponibilite) values ( 'Banquete' ,'CH', 0, true  );
INSERT INTO  chaise(name, code, stock, disponibilite) values ( 'chaise' ,'CH', 0, true  );
INSERT INTO  chaise(name, code, stock, disponibilite) values ( 'chaise barcelone' ,'CH', 0, true  );
INSERT INTO  user(username, password) values ('mohammed' ,'$2a$10$pwQD6YVCMbiZrVv88e7WveZRVrSsFzCtfFzZsNgMWCKaF3w33dZXy'  ); 
INSERT INTO  user(username, password) values ('khattala' ,'$2a$10$hzVc1ZXrcFsddMqpO0Njr.993Xob7ATZ0SUeu2.1a97SDTNcBHWfW'  );
INSERT INTO  role(name) values ( 'admin' );
INSERT INTO  role(name) values ( 'employe' );  
INSERT INTO  user_role(role_id, user_id) values ( 1 , 1 ); 
INSERT INTO  user_role(role_id, user_id) values ( 2 , 1 ); 
INSERT INTO  user_chaise(chaise_id, user_id) values ( 1 , 1 ); 
INSERT INTO  user_livre(livre_id, user_id) values ( 1 , 2 ); 



