;             
CREATE USER IF NOT EXISTS SA SALT '26b73103ed610eec' HASH '3c58b270f10dd9212885623a592eb9d07ef11811fe4f4c861d525483e013b456' ADMIN;           
//CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7E043AD2_DC9B_4959_AE30_58229BD7EA63 START WITH 3 BELONGS_TO_TABLE;
//CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_88EA802D_7A2B_468A_944F_3CBEFF2966D3 START WITH 2 BELONGS_TO_TABLE;
CREATE CACHED TABLE PUBLIC.MATERIEL(
    ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_7E043AD2_DC9B_4959_AE30_58229BD7EA63) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_7E043AD2_DC9B_4959_AE30_58229BD7EA63,
    NAME VARCHAR(250) NOT NULL,
    CODE VARCHAR(250) NOT NULL
);     
ALTER TABLE PUBLIC.MATERIEL ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);               
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.MATERIEL;
INSERT INTO PUBLIC.MATERIEL(ID, NAME, CODE) VALUES
(1, 'Livre', 'LI'),
(2, 'Chaise', 'CH'); 
CREATE CACHED TABLE PUBLIC.USER(
    ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_88EA802D_7A2B_468A_944F_3CBEFF2966D3) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_88EA802D_7A2B_468A_944F_3CBEFF2966D3,
    USERNAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(256) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    REGISTRATION_DATE DATE DEFAULT CURRENT_TIMESTAMP() NOT NULL,
    LOCKED BOOLEAN DEFAULT TRUE NOT NULL
);  
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
INSERT INTO PUBLIC.USER(ID, USERNAME, PASSWORD, EMAIL, REGISTRATION_DATE, LOCKED) VALUES
(1, 'guest', '$2a$10$rBfCynQZMak0G2MoWA/2aubD3lkJiby7qZgoOTodbN5qnab38CS3m', 'elmess0203@gmail.com', DATE '2022-06-02', TRUE);      
