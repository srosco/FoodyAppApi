-- V1__initial_schema.sql
 DROP database foody_api;
-- Create the database (if not already created)
CREATE DATABASE IF NOT EXISTS foody_api;

-- Use the created database
USE foody_api;

-- Now create the tables
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    current_maccros VARCHAR(255),
    aimed_maccros VARCHAR(255) NOT NULL,
    cart_id BIGINT,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    proteins DOUBLE,
    fibers DOUBLE,
    calories DOUBLE,
    carbohydrates DOUBLE
);

CREATE TABLE cart (
    cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    total_proteins DOUBLE,
    total_carbohydrates DOUBLE,
    total_fibers DOUBLE,
    total_calories DOUBLE,
    user_id BIGINT,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

CREATE TABLE cart_products (
    cart_id BIGINT,
    product_id BIGINT,
    PRIMARY KEY (cart_id , product_id),
    FOREIGN KEY (cart_id)
        REFERENCES cart (cart_id)
        ON DELETE CASCADE,
    FOREIGN KEY (product_id)
        REFERENCES products (id)
        ON DELETE CASCADE
);
