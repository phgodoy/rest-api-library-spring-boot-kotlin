-- Users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_id INT REFERENCES address(id),
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    identification_code VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);