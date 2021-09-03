CREATE TABLE "doctor_user" (
    id SERIAL UNIQUE,
    username varchar(30),
    password varchar(100)
);

ALTER TABLE "doctor_user" ADD CONSTRAINT doctor_user_pkey PRIMARY KEY (id);


CREATE TABLE "doctor" (
    id SERIAL UNIQUE,
    name varchar(90),
    phone_number varchar(30),
    cpf varchar(20),
    gender varchar(20),
    doctor_user_id int
);

ALTER TABLE "doctor" ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);

ALTER TABLE "doctor"
    ADD CONSTRAINT doctor_fk_1 FOREIGN KEY (doctor_user_id) REFERENCES doctor_user(id) ON DELETE CASCADE;


CREATE TABLE "address" (
    id SERIAL UNIQUE,
    zip_code varchar(15),
    street_name varchar(50),
    number int,
    additional_info varchar(30),
    neighborhood varchar(30),
    city varchar(50),
    state varchar(50)
);

ALTER TABLE "address" ADD CONSTRAINT address_pkey PRIMARY KEY (id);


CREATE TABLE "patient" (
    id SERIAL UNIQUE,
    name varchar(90),
    cpf varchar(20),
    phone_number varchar(30),
    observation text,
    doctor_id int,
    address_id int
);

ALTER TABLE "patient" ADD CONSTRAINT patient_pkey PRIMARY KEY (id);
ALTER TABLE "patient"
    ADD CONSTRAINT patient_fk_1 FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE SET NULL;
ALTER TABLE "patient"
    ADD CONSTRAINT patient_fk_2 FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE SET NULL;