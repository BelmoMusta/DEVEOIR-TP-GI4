DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE IF NOT EXISTS material_type
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS material
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,

    material_type INT NOT NULL,

    time_rented INT NOT NULL,

    available boolean NOT NULL,

    stock INT NOT NULL,

    FOREIGN KEY(material_type) REFERENCES material_type(id)
);

CREATE TABLE user
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(100) NOT NULL,

    first_name VARCHAR(250) NOT NULL,

    last_name VARCHAR(250) NOT NULL,

    password varchar(100) NOT NULL,
);

CREATE TABLE IF NOT EXISTS rented_material
(
    id INT AUTO_INCREMENT PRIMARY KEY,

    user_id INT NOT NULL,

    material_id INT NOT NULL,

    deadline DATE NOT NULL,

    FOREIGN KEY(user_id) REFERENCES user(id),

    FOREIGN KEY(material_id) REFERENCES material(id)
);

CREATE TABLE roles(
    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(250) NOT NULL,
);

CREATE TABLE user_roles(
    id INT AUTO_INCREMENT PRIMARY KEY,

    user_id INT NOT NULL,

    role_id INT NOT NULL,

    FOREIGN KEY(user_id) REFERENCES user(id),

    FOREIGN KEY(role_id) REFERENCES roles(id),

)



