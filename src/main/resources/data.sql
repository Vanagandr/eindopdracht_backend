insert into roles(rolename) values ('ROLE_ADMINISTRATION'), ('ROLE_MECHANIC'), ('ROLE_SUPPLY');

--Insert users
insert into users(username, password) values ('SuperUser', '$2a$12$W38ulbhtl2JT2eb9.B7GleUEx.eyqF.ftmgQ.tqYJHiUW6cj2BBk2'),
                                             ('TestMechanic','$2a$12$W38ulbhtl2JT2eb9.B7GleUEx.eyqF.ftmgQ.tqYJHiUW6cj2BBk2'),
                                             ('TestAdministration', '$2a$12$W38ulbhtl2JT2eb9.B7GleUEx.eyqF.ftmgQ.tqYJHiUW6cj2BBk2'),
                                             ('TestSupply', '$2a$12$W38ulbhtl2JT2eb9.B7GleUEx.eyqF.ftmgQ.tqYJHiUW6cj2BBk2');

--Insert users into roles
insert into users_roles (user_id, role_id) values ('SuperUser', 'ROLE_ADMINISTRATION'),
                                                  ('SuperUser', 'ROLE_MECHANIC'),
                                                  ('SuperUser', 'ROLE_SUPPLY'),
                                                  ('TestMechanic', 'ROLE_MECHANIC'),
                                                  ('TestAdministration', 'ROLE_ADMINISTRATION'),
                                                  ('TestSupply', 'ROLE_SUPPLY');
--Insert Customers
INSERT INTO customers (last_name, city, phone_number) VALUES
                                                          ('Jansen', 'Amsterdam', '0612345678'),
                                                          ('Peters', 'Rotterdam', '0687654321'),
                                                          ('Smith', 'Utrecht', '0698765432');
-- Insert cars
INSERT INTO cars (registration, brand, year_of_construct, inspection_date, repair_date, customer_id, agree_repair) VALUES
                                                                                                         ('AB-123-YZ', 'Toyota', '2020', NULL, NULL, NULL, FALSE),
                                                                                                         ('XY-456-ZT', 'Honda', '2019', NULL, NULL, NULL, FALSE);

--Insert Parts
INSERT INTO parts (type, price, quantity) VALUES
                                              ('Band', 100, 1),
                                              ('Accu', 120, 1),
                                              ('Motorolie', 40, 1),
                                              ('Remblokken', 70, 1),
                                              ('Dynamo', 200, 1),
                                              ('Waterpomp', 90, 1),
                                              ('Brandstoffilter', 30, 1),
                                              ('Luchtfilter', 20, 1),
                                              ('Koplampen', 150, 1),
                                              ('Radiateur', 180, 1);

--Insert Repairs
INSERT INTO repairs (type, price) VALUES
                                      ('Inspectie', 45),
                                      ('Band Vervangen', 100),
                                      ('Olie Verversen', 50),
                                      ('Accu Vervangen', 200),
                                      ('Remblokken Vervangen', 70),
                                      ('Dynamo Vervangen', 250),
                                      ('Waterpomp Vervangen', 150),
                                      ('Brandstoffilter Vervangen', 40),
                                      ('Luchtfilter Vervangen', 30),
                                      ('Koplampen Vervangen', 200),
                                      ('Radiateur Vervangen', 180);
