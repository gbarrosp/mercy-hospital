CREATE TABLE doctor (
    id SERIAL UNIQUE,
    name varchar(90),
    username varchar(30),
    password varchar(100),
    phone_number varchar(90),
    cpf varchar(20),
    gender varchar(20)
);


ALTER TABLE doctor ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);