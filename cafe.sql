
SQL Queries -

CREATE TABLE customers(
id serial NOT NULL PRIMARY KEY,
name VARCHAR(50),
email VARCHAR(50),
number VARCHAR(50),
bill integer
)

CREATE TABLE orders(
orderid SERIAL NOT NULL PRIMARY KEY,
id integer,
dish integer
qunt integer,
price integer,
amnt integer,
FOREIGN KEY(id) REFERENCES customers(id)
);

CREATE TABLE menu(
id integer NOT NULL PRIMARY KEY,
dish VARCHAR(50),
price integer
);

INSERT INTO customers(name, email, number)
VALUES('aisha ahmed', 'aisha@gmail.com', '7655675876');

INSERT INTO orders(id, dish, qunt) VALUES(12, 'maggi', 3);

INSERT INTO menu(id, dish, price) VALUES(8, 'Chilli Cheese Toast', 100);

SELECT * FROM customers ORDER BY id;

SELECT * FROM orders ORDER BY orderid;

SELECT * FROM menu ORDER BY id;

UPDATE orders
SET price= menu.price, amnt=qunt*orders.price
FROM menu WHERE orders.dish=menu.dish;

UPDATE customers
SET bill=(SELECt sum(amnt) FROM orders
WHERE id=12 GROUP BY id)
WHERE id=12;

SELECT orders.id, orderid, name, number, dish, qunt, amnt, bill FROM customers INNER JOIN orders ON customers.id=orders.id ORDER BY orderid;

SELECT orders.id, orderid, name, number, dish, qunt, amnt, bill FROM customers INNER JOIN orders ON customers.id=orders.id WHERE orders.id=5;







