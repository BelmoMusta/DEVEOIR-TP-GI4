
INSERT INTO  materiel(name, type, author, edition, stock, allocated) values ( 'Boite A Merveilles' ,'LIVRE', 'Ahmed Sefroui', 'MoroccoPrint', 2, 0);
INSERT INTO  materiel(name, type, wood, stock, allocated) values ( 'Chaise A32', 'CHAISE', 'Hetre', 5, 0);

INSERT INTO Roles(id, name) values (1, 'ADMIN'), (2, 'USER');

INSERT INTO USER(ID, USERNAME, PASSWORD, EMAIL, REGISTRATION_DATE, LOCKED) VALUES
    (1, 'root', '$2a$10$sS9uSHqyao20zMybuh8/6uPdnYALUMlepStxtmypYNR6V4s0I6Xf6', 'elmess0203@gmail.com', DATE '2022-06-02', FALSE);
INSERT INTO USER(ID, USERNAME, PASSWORD, EMAIL, REGISTRATION_DATE, LOCKED) VALUES
    (2, 'mehdi0203', '$2a$10$sS9uSHqyao20zMybuh8/6uPdnYALUMlepStxtmypYNR6V4s0I6Xf6', 'elmessmehdi@gmail.com', DATE '2022-06-08', FALSE);

INSERT INTO user_roles(id_user, id_role) VALUES (1, 1), (1,2), (2,2);