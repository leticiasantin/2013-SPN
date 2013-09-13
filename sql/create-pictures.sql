CREATE TABLE spn.client_service_picture(
	picture_id SERIAL PRIMARY KEY,
	service_id INT REFERENCES spn.service_evaluation (service_id),
	path VARCHAR(200)
);

CREATE TABLE spn.provider_service_picture(
	picture_id SERIAL PRIMARY KEY,
	service_id INT REFERENCES spn.service_evaluation (service_id),
	path VARCHAR(200)
);

CREATE TABLE spn.homepage_picture(
	picture_id SERIAL PRIMARY KEY,
	provider_id INT REFERENCES spn.provider (provider_id),
	path VARCHAR(50)
)