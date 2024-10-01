insert into roles(rolename) values ('ROLE_ADMINISTRATION'), ('ROLE_MECHANIC'), ('ROLE_SUPPLY');

--Insert Customers
INSERT INTO customers (last_name, city, phone_number) VALUES
                                                          ('Jansen', 'Amsterdam', '0612345678'),
                                                          ('Peters', 'Rotterdam', '0687654321'),
                                                          ('Smith', 'Utrecht', '0698765432');
-- Insert cars
INSERT INTO cars (registration, brand, year_of_construct, inspection_date, repair_date, customer_id) VALUES
                                                                                                         ('AB-123-YZ', 'Toyota', '2020', NULL, NULL, NULL),
                                                                                                         ('XY-456-ZT', 'Honda', '2019', NULL, NULL, NULL);

--Insert Parts
INSERT INTO parts (type, price, quantity) VALUES
                                              ('Band', 100, 20),
                                              ('Accu', 120, 15),
                                              ('Motorolie', 40, 50),
                                              ('Remblokken', 70, 30),
                                              ('Dynamo', 200, 10),
                                              ('Waterpomp', 90, 25),
                                              ('Brandstoffilter', 30, 40),
                                              ('Luchtfilter', 20, 35),
                                              ('Koplampen', 150, 18),
                                              ('Radiateur', 180, 12);

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
