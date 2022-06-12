
INSERT INTO  Utilisateur(username, Password,role) values ( 'admin' ,'25f9e794323b453885f5181f1b624d0b','admin' );
INSERT INTO  Utilisateur(username, Password,role) values ( 'user1' ,'827ccb0eea8a706c4c34a16891f84e7b','user' );

INSERT INTO  materiel(user_id, user_username, name, code, disponible, alloue, duree) values ( '2', 'user1', 'LIVRE' ,'KL', FALSE, TRUE, '4J');

