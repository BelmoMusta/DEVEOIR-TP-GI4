
INSERT INTO utilisateur(username, password, role) values ( 'said' ,'1234' ,'admin');
INSERT INTO utilisateur(username, password, role) values ( 'hmad' ,'1234', 'employe');
INSERT INTO utilisateur(username, password, role) values ( 'brahim' ,'1234', 'employe');
INSERT INTO utilisateur(username, password, role) values ( 'karim' ,'1234', 'employe');

INSERT INTO  materiel(name, code) values ( 'LIVRE' ,'LI');
INSERT INTO  materiel(name, code) values ( 'CHAISE' ,'CH');
INSERT INTO  materiel(utilisateur_id, utilisateur_username, name, code, disponible, alloue, duree) values ( 1, 'said', 'LIVRE' ,'KL', TRUE, TRUE, '1J');
INSERT INTO  materiel(name, code) values ( 'CHAISE' ,'MN');
INSERT INTO  materiel(utilisateur_id, utilisateur_username, name, code, disponible, alloue, duree) values ( 4, 'karim', 'CHAISE' ,'PO', TRUE, TRUE, '2J');



