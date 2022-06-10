
INSERT INTO  materiel(name, code, qte,disponible) values ( 'Livre' ,'LI', 5, TRUE );
INSERT INTO  materiel(name, code, qte,disponible) values ( 'Chaise' ,'CH', 2 ,TRUE );

INSERT INTO  roles(name ) values ( 'admin' );
INSERT INTO  roles(name ) values ( 'Employee' );

INSERT INTO  users(username, password, idR) values ( 'kaouthar', 'ka1234', 1 );
INSERT INTO  users(username, password, idR) values ( 'houda', 'ha1234', 2 );

INSERT INTO allocations(qta,rendu,idM,idU) values (1,false,2,2)
