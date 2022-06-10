INSERT INTO user(username, first_name, last_name, password)
                 VALUES('mohamed1302', 'mohamed', 'el kadiri', 'c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646');
INSERT INTO user(username, first_name, last_name, password)
                  VALUES('gilgamesh1302', 'mohamed', 'el kadiri', 'c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646');
INSERT INTO roles(name) VALUES('administrateur');
INSERT INTO roles(name) VALUES('employee');
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);
