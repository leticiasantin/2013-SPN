CREATE TABLE spn.service (
 service_id SERIAL,
 client_id INT,
 provider_id INT,
 cli_fb_comment VARCHAR(256),
 cli_fb_note smallint,
 pro_fb_comment VARCHAR(256),
 pro_fb_note smallint,
 is_completed boolean,
 PRIMARY KEY (service_id,client_id,provider_id),
 FOREIGN KEY (client_id) REFERENCES spn.client (client_id),
 FOREIGN KEY (provider_id) REFERENCES spn.provider (provider_id)
);


CREATE TABLE spn.category (
 cat_id SERIAL NOT NULL PRIMARY KEY,
 parent_id INT,
 name VARCHAR(15)
 FOREIGN KEY (parent_id) REFERENCES spn.category(cat_id)
 );


 CREATE TABLE spn.user (
 user_id SERIAL NOT NULL PRIMARY KEY,
 name VARCHAR(50),
 login VARCHAR(15) NOT NULL UNIQUE,
 password CHAR(8) NOT NULL,
 dt_of_birth CHAR(10),
 city VARCHAR(30),
 state CHAR(2),
 street VARCHAR(30),
 number smallint,
 complements VARCHAR(10),
 neighborhood VARCHAR(15),
 zip_code CHAR(8)
 );



 CREATE TABLE spn.picture(
 picture_id SERIAL NOT NULL PRIMARY KEY,
 image VARCHAR(20), 
 service_id INT,
 FOREIGN KEY (service_id) REFERENCES spn.service (service_id)
 );

 CREATE TABLE spn.profile(
 profile_id SERIAL NOT NULL PRIMARY KEY,
 description VARCHAR(1024),
 picture VARCHAR(30)
 );

 CREATE TABLE spn.provider(
 provider_id int primary key,
 profile_id int,
 FOREIGN KEY (profile_id) REFERENCES spn.profile (profile_id)
 );


 CREATE TABLE spn.prov_has_cat (
 provider_id INT,
 cat_id INT,
 PRIMARY KEY (provider_id, cat_id),
 FOREIGN KEY (provider_id) REFERENCES spn.provider (provider_id),
 FOREIGN KEY (cat_id) REFERENCES spn.category (cat_id)
 );


CREATE TABLE spn.client(
client_id INT PRIMARY KEY,
FOREIGN KEY (client_id) REFERENCES spn.user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
)


