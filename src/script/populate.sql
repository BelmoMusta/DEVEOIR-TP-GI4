--
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Livre' ,'LI', true, 40);
INSERT INTO  materiel(name, code, availability, quantity) values ( 'Chaise' ,'CH', true, 30 );

INSERT INTO users(username, password, role) values ('adm','42ef63e7836ef622d9185c1a456051edf16095cc', 'administrateur');
INSERT INTO users(username, password, role) values ('empl', 'ecab8a146c22e7f8ceb3ee79b3aac9fb8f553f53', 'employée');
INSERT INTO users(username, password, role) values ('admin','d033e22ae348aeb5660fc2140aec35850c4da997', 'administrateur , employée');

