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
    user_id BIGINT, 
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    cart_id VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL
);

CREATE TABLE cart_products (
    cart_id BIGINT,
    product_id BIGINT,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);