-- Address table
CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    street VARCHAR(90) NOT NULL,
    number INT NOT NULL,
    neighbourhood VARCHAR(90) NOT NULL,
    complement VARCHAR(50) NOT NULL,
    postalcode INT NOT NULL,
    city VARCHAR(90) NOT NULL,
    state VARCHAR(90) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    status INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);