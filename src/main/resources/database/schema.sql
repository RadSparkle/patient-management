create sequence IF NOT EXISTS HIBERNATE_SEQUENCE start with 1 increment by 1;

create table IF NOT EXISTS code_group (
    code_group VARCHAR(10) NOT NULL,
    code_group_name VARCHAR(10),
    description VARCHAR(10),

    PRIMARY KEY (code_group)
    );

create table IF NOT EXISTS codes (
    code_group VARCHAR(10) NOT NULL,
    code VARCHAR(10) NOT NULL,
    code_name VARCHAR(10) NOT NULL,

    PRIMARY KEY (code),
    FOREIGN KEY (code_group) REFERENCES code_group(code_group)
    );

create table IF NOT EXISTS hospitals (
    hospital_id BIGINT NOT NULL AUTO_INCREMENT,
    hospital_name VARCHAR(45) NOT NULL,
    nursing_home_number VARCHAR(20) NOT NULL,
    hospital_director_name VARCHAR(10) NOT NULL,

    PRIMARY KEY (hospital_id)
    );

create table IF NOT EXISTS patients (
   patient_id BIGINT NOT NULL AUTO_INCREMENT,
   hospital_id BIGINT NOT NULL,
   patient_name VARCHAR(45) NOT NULL,
   patient_registration_number VARCHAR(13) NOT NULL,
   gender_code VARCHAR(10) NOT NULL,
   birth_date VARCHAR(10) NOT NULL,
   mobile_phone VARCHAR(20) NOT NULL,

   PRIMARY KEY (patient_id)
    );

create table IF NOT EXISTS visit (
   patient_visit_id BIGINT NOT NULL AUTO_INCREMENT,
   hospital_id BIGINT NOT NULL,
   patient_id BIGINT NOT NULL,
   registration_date DATETIME NOT NULL,
   visit_status_code VARCHAR(10) NOT NULL,

   PRIMARY KEY (patient_visit_id),
   FOREIGN KEY (hospital_id) REFERENCES hospitals (hospital_id),
   FOREIGN KEY (patient_id) REFERENCES patients (patient_id)
    );

alter table patients
    add constraint IF NOT EXISTS hospitalIdFK
    foreign key (hospital_id)
    references hospitals;