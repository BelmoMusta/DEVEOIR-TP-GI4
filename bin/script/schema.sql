
DROP TABLE IF EXISTS user;

CREATE TABLE User
(
    userid INT AUTO_INCREMENT UNIQUE,

    username VARCHAR(250) NOT NULL UNIQUE,

    password VARCHAR(250) NOT NULL,
    
    role VARCHAR(250) DEFAULT 'employee' NOT NULL,
    
    PRIMARY KEY (userid)

);


DROP TABLE IF EXISTS materiel;

CREATE TABLE materiel
(
    matId INT AUTO_INCREMENT UNIQUE,

    name VARCHAR(250) NOT NULL UNIQUE,
    
    type VARCHAR(250) NOT NULL,

    alloue BOOLEAN DEFAULT false NOT NULL,
        
    userId INT,
    
    PRIMARY KEY (matId),
    FOREIGN KEY (userid)
      REFERENCES User(userid)
      ON DELETE CASCADE

);



