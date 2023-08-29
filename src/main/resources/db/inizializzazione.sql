-- Crea il database
CREATE DATABASE awesome_pizza;

-- Usa il database creato
USE awesome_pizza;

-- Crea la tabella per le pizze
CREATE TABLE pizzas (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        cost DOUBLE NOT NULL,
                        ingredients JSON,
                        UNIQUE KEY (name)
);

-- Crea la tabella per gli ordini
CREATE TABLE orders (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        status VARCHAR(255) NOT NULL,
                        importo DOUBLE NOT NULL
);

-- Crea la tabella per gli ordini delle pizze
CREATE TABLE pizza_order (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             order_id INT NOT NULL,
                             pizza_id INT NOT NULL,
                             quantity INT NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (pizza_id) REFERENCES pizzas(id)
);
