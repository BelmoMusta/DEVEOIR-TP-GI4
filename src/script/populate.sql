INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('EMPLOYEE');
INSERT INTO users (username,hashed_password,role_id) VALUES ( 'username','$argon2id$v=19$m=15360,t=2,p=1$vA4YjosJ3tIsiE3JyYpRVBb7u4MxUznme2vJHTJ4MSM$Shf4uZ2afWzbHllM0edENIG787QvG3aOPtDwNR06tsMm/jAbgG932XOv2HIj459H9Fanu4bsrMAABfwgpmG7DA',1 );
INSERT INTO materials (name,quantity,material_type) VALUES ('Livre',15,'LI')


