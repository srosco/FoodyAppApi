-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS bank_account;

-- CREATE TABLE bank_account (
--                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
--                               name VARCHAR(255) NOT NULL,
--                               balance DOUBLE NOT NULL
-- );

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       mail VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       salt VARCHAR(255) NOT NULL
                    --    bank_account_id BIGINT UNIQUE,
);

CREATE TABLE products (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       category VARCHAR(255) NOT NULL,
                       proteins VARCHAR(255) UNIQUE NOT NULL,
                       fibers VARCHAR(255) UNIQUE NOT NULL,
                       calories VARCHAR(255) UNIQUE NOT NULL,
                       carbohydrates VARCHAR(255) UNIQUE NOT NULL
);
