CREATE TABLE spn.service(
	service_id SERIAL PRIMARY KEY,
	client_id INT references spn.client(client_id) ON UPDATE CASCADE ON DELETE SET NULL,
	provider_id INT references spn.provider(provider_id) ON UPDATE CASCADE ON DELETE SET NULL,
	category_id INT references spn.category(cat_id) ON UPDATE CASCADE ON DELETE SET NULL,
	client_request_dat TIMESTAMP DEFAULT 'NOW',
	provider_response_dat TIMESTAMP,
	scheduled_date TIMESTAMP,
	estimated_date INT
);


CREATE TABLE spn.service_evaluation(
	service_id INT PRIMARY KEY REFERENCES spn.service(service_id) ON UPDATE CASCADE ON DELETE CASCADE,
	provider_time_spent INT,
	provider_price NUMERIC(10,2) CHECK (provider_price >= 0.0),
	provider_rating smallint CHECK (provider_rating > 0 AND provider_rating <= 5),
	provider_evaluation VARCHAR(200),
	client_payment smallint CHECK (client_payment > 0 AND client_payment <= 5),
	client_rating smallint CHECK (client_rating > 0 AND client_rating <= 5),
	client_evaluation VARCHAR(200)	
);
