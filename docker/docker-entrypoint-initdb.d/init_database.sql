-- Cria o banco de dados "library_db"
CREATE DATABASE library_db;

-- Define o usuário e a senha para o banco de dados
CREATE USER postgres WITH ENCRYPTED PASSWORD '1234';
GRANT ALL PRIVILEGES ON DATABASE library_db TO postgres;