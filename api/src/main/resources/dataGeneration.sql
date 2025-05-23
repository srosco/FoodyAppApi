-- Drop the existing database if it exists
DROP DATABASE IF EXISTS foody_api;

-- Create the database (if not already created)
CREATE DATABASE IF NOT EXISTS foody_api;

-- Use the created database
USE foody_api;

-- Create the 'user' table
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    current_maccros DOUBLE,
    aimed_maccros DOUBLE,
	aimed_carbohydrates DOUBLE,
	aimed_fibers DOUBLE,
	aimed_proteins DOUBLE,
	aimed_calories DOUBLE,
    cart_id BIGINT,  -- Refers to the cart entity
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL
);

-- Create the 'products' table with a version field for optimistic locking
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    category VARCHAR(255),
    proteins DOUBLE,
    fibers DOUBLE,
    calories DOUBLE,
    carbohydrates DOUBLE,
    CONSTRAINT unique_name UNIQUE (name)  -- Enforcing unique product name
);

-- Create the 'cart' table with a version field for optimistic locking
CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Updated column name to 'id'
    creation_date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    total_proteins DOUBLE,
    total_carbohydrates DOUBLE,
    total_fibers DOUBLE,
    total_calories DOUBLE,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);

CREATE TABLE cart_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Single primary key
    cart_id BIGINT NOT NULL,  -- Foreign key to cart
    product_id BIGINT NOT NULL,  -- Foreign key to product
    quantity_in_grams DOUBLE NOT NULL,  -- Quantity of the product in grams
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
