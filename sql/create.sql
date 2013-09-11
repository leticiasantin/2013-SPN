CREATE SCHEMA spn;


CREATE TABLE spn.user
(
  user_id serial NOT NULL,
  name character varying(50),
  login character varying(15) NOT NULL,
  password character(32) NOT NULL,
  dt_of_birth character(10),
  city character varying(30),
  state character(2),
  street character varying(30),
  "number" integer,
  complements character varying(10),
  neighborhood character varying(15),
  zip_code character(8),
  status boolean DEFAULT true,
  gender "char" NOT NULL DEFAULT 'F'::"char",
  CONSTRAINT user_pkey PRIMARY KEY (user_id ),
  CONSTRAINT user_login_key UNIQUE (login )
);

CREATE TABLE spn.provider
(
  provider_id integer NOT NULL,
  status boolean DEFAULT true,
  CONSTRAINT provider_pkey PRIMARY KEY (provider_id ),
  CONSTRAINT fk_provider_user FOREIGN KEY (provider_id)
      REFERENCES spn."user" (user_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE spn.client
(
  client_id integer NOT NULL,
  status boolean DEFAULT true,
  CONSTRAINT client_pkey PRIMARY KEY (client_id ),
  CONSTRAINT client_client_id_fkey FOREIGN KEY (client_id)
      REFERENCES spn."user" (user_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE spn.profile
(
  profile_id serial NOT NULL,
  description character varying(1024),
  picture character varying(30),
  provider_id integer NOT NULL,
  CONSTRAINT profile_pkey PRIMARY KEY (profile_id ),
  CONSTRAINT fk_profile_provider FOREIGN KEY (provider_id)
      REFERENCES spn.provider (provider_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE spn.picture
(
  picture_id serial NOT NULL,
  image character varying(20),
  service_id integer,
  CONSTRAINT picture_pkey PRIMARY KEY (picture_id )
);


CREATE TABLE spn.category
(
  cat_id serial NOT NULL,
  parent_id integer,
  name character varying(50) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (cat_id ),
  CONSTRAINT fk_cat_cat FOREIGN KEY (parent_id)
      REFERENCES spn.category (cat_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE spn.prov_has_cat
(
  provider_id integer NOT NULL,
  cat_id integer NOT NULL,
  CONSTRAINT prov_has_cat_pkey PRIMARY KEY (provider_id , cat_id ),
  CONSTRAINT fk_cat_prov_has_cat FOREIGN KEY (cat_id)
      REFERENCES spn.category (cat_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_provider_prov_has_cat FOREIGN KEY (provider_id)
      REFERENCES spn.provider (provider_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE spn.service
(
  service_id serial NOT NULL,
  client_id integer NOT NULL,
  provider_id integer NOT NULL,
  cli_fb_comment character varying(256),
  cli_fb_note smallint,
  pro_fb_comment character varying(256),
  pro_fb_note smallint,
  is_completed boolean,
  CONSTRAINT service_pkey PRIMARY KEY (service_id , client_id , provider_id ),
  CONSTRAINT fk_ser_cli FOREIGN KEY (client_id)
      REFERENCES spn.client (client_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ser_pro FOREIGN KEY (provider_id)
      REFERENCES spn.provider (provider_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);





INSERT INTO spn."user"(
             name, login, password, dt_of_birth, city, state, street, 
            "number", complements, neighborhood, zip_code)
    VALUES ('João da Silva','js',md5('1'),'12-06-1980','Londrina','PR','Rua das Acacias',
		32,'apto 56', 'vila jamaica','88888888'),
		('Jose Souza','zes',md5('1'),'12-06-1980','Londrina','PR','Rua Maranhão',
		22,'', 'vila imigrantes','82888888'),
		('Maria Amadora','maria',md5('1'),'12-08-1980','Tubarão','PR','Rua Esperança',
		89,'', 'vila jamaica','88088888');



SELECT * FROM spn.user
