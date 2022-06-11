--
-- INSERT INTO  materiel(name, code) values ( 'Livre' ,'LI'  );
-- INSERT INTO  materiel(name, code) values ( 'Chaise' ,'CH'  );
-- INSERT INTO  materiel(name, code,type) values ('Livre1' ,'12' ,'Livre'  );
-- INSERT INTO  materiel(name, code,type) values ( 'Chaise1','13' ,'Chaise'  );
--
INSERT INTO  Utilisateur(username, Password,role) values ( 'admin' ,'123456789','admin' );
INSERT INTO  Utilisateur(username, Password,role) values ( 'user1' ,'12345','user' );
--
INSERT INTO  materiel(user_id, user_username, name, code, disponible, alloue, duree) values ( '1', 'user1', 'LIVRE' ,'KL', TRUE, TRUE, '4J');
--
