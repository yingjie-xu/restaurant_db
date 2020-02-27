/**
 * Group 62:
 * This file contains all the create table statements
 */

-- DROP TABLE orders, dine_in_orders, delivery_orders, staff, waiter, delivery_guy, chef, platform, customer, dish, reservation, contain, cooked_by, served_by;

CREATE TABLE customer
(
   phone_number VARCHAR(15) NOT NULL
  ,canme VARCHAR(40)
  ,address VARCHAR(100)
  ,PRIMARY KEY(phone_number)
);

CREATE TABLE staff
(
   sid INTEGER NOT NULL
  ,sname VARCHAR(40)
  ,working_schdule VARCHAR(12)
  ,salary FLOAT8
  ,PRIMARY KEY(sid)
);

CREATE TABLE platform
(
   pname VARCHAR(30) NOT NULL
  ,url VARCHAR(100)
  ,PRIMARY KEY(pname)
);

CREATE TABLE orders
(
   order_number VARCHAR(30) NOT NULL
  ,tips FLOAT8
  ,PRIMARY KEY(order_number)
); 

CREATE TABLE dine_in_orders
(
   order_number VARCHAR(30) NOT NULL
  ,phone_number VARCHAR(15) NOT NULL
  ,PRIMARY KEY(order_number)
  ,FOREIGN KEY(order_number) REFERENCES orders(order_number)
  ,FOREIGN KEY(phone_number) REFERENCES customer(phone_number)
); 

CREATE TABLE delivery_orders
(
   order_number VARCHAR(30) NOT NULL
  ,phone_number VARCHAR(15) NOT NULL
  ,sid INTEGER NOT NULL
  ,pname VARCHAR(30)
  ,delivery_fee FLOAT8
  ,PRIMARY KEY(order_number)
  ,FOREIGN KEY(order_number) REFERENCES orders(order_number)
  ,FOREIGN KEY(sid) REFERENCES staff(sid)
  ,FOREIGN KEY(pname) REFERENCES platform(pname)
); 

CREATE TABLE waiter
(
   sid INTEGER NOT NULL
  ,FOREIGN KEY(sid) REFERENCES staff(sid)
  ,PRIMARY KEY(sid)
);

CREATE TABLE delivery_guy
(
   delivery_method VARCHAR(12)
  ,phone_number VARCHAR(15)
  ,sid INTEGER NOT NULL
  ,FOREIGN KEY(sid) REFERENCES staff(sid)
  ,PRIMARY KEY(sid)
);

CREATE TABLE chef
(
   proficiency INTEGER
  ,cooking_style VARCHAR(12)
  ,sid INTEGER NOT NULL
  ,FOREIGN KEY(sid) REFERENCES staff(sid)
  ,PRIMARY KEY(sid)
);

CREATE TABLE dish
(
   dish_name VARCHAR(50) NOT NULL
  ,price FLOAT8
  ,PRIMARY KEY(dish_name)
);

CREATE TABLE reservation
(  
   rdate DATE
  ,phone_number VARCHAR(15)
  ,timeslot TIME
  ,PRIMARY KEY(rdate, phone_number)
  ,FOREIGN KEY(phone_number) REFERENCES customer(phone_number)
);

CREATE TABLE contain
(  
   order_number VARCHAR(20)
  ,dish_name VARCHAR(50)
  ,quantity INTEGER
  ,PRIMARY KEY(order_number, dish_name)
  ,FOREIGN KEY(order_number) REFERENCES orders(order_number)
  ,FOREIGN KEY(dish_name) REFERENCES dish(dish_name)
);

CREATE TABLE cooked_by
(
   dish_name VARCHAR(50)
  ,sid INTEGER
  ,PRIMARY KEY(dish_name, sid)
  ,FOREIGN KEY(sid) REFERENCES chef(sid)
  ,FOREIGN KEY(dish_name) REFERENCES dish(dish_name)
); 

CREATE TABLE served_by
(  
   order_number VARCHAR(20)
  ,sid INTEGER
  ,PRIMARY KEY(order_number, sid)
  ,FOREIGN KEY(order_number) REFERENCES orders(order_number)
  ,FOREIGN KEY(sid) REFERENCES staff(sid)
);