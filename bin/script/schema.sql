DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS allocation;

CREATE TABLE user 
(
	id   INT AUTO_INCREMENT PRIMARY KEY,
	
	name VARCHAR(250) NOT NULL,
	
	password VARCHAR(250) NOT NULL,
	
	role VARCHAR(250) NOT NULL,
); 


CREATE TABLE materiel
(
    id   INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    code VARCHAR(250) NOT NULL,
    
    stock INT, 
    
    disponibilite BOOLEAN, 
    
);

CREATE TABLE allocation 
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	
	idMateriel INT NOT NULL, 
	
	idUser INT NOT NULL, 
	
	dateAllocation TIMESTAMP NOT NULL, 
	
	CONSTRAINT FK_idMateriel FOREIGN KEY (idMateriel)
    REFERENCES materiel(id), 
    
    CONSTRAINT FK_idUser FOREIGN KEY (idUser)
    REFERENCES user(id), 
    
);



