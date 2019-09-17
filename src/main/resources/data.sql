
replace INTO role (id,role) VALUES (1,'ROLE_ADMIN');
replace INTO role (id,role) VALUES (2,'ROLE_USER');
replace into employee (id, active, email, first_name, last_name, login, password) values ( 1,1,'admin@admin.pl', 'admin' , 'admin','admin','$2a$10$CPUI9dBhGc098R5jPaBuT.zNi1G5.pshSx1kqDEqh2M/5TYYPNqyq');
replace into employee_role (employee_id,role_id) values ( 1,1 );

replace into car (id,mark,model) values (1, 'ford', 'mondeo');
replace into car_unit (id, registration, car_id) values (1, 'rze12345',1);

