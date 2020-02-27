/**
 * Group 62:
 * This file contains all the insert statements
 */

-- Customer
INSERT INTO customer VALUES('514-123-8900','Nimrah Carter','190  Lynden Road');
INSERT INTO customer VALUES('514-421-1768','Tiana House','1528  Halsey Avenue');
INSERT INTO customer VALUES('514-141-5854','Armaan Gibbons','3569  Lockhart Drive');
INSERT INTO customer VALUES('514-133-1567','Jevan Hope','3020  rue Parc');
INSERT INTO customer VALUES('514-140-1264','Nettie Cline','2318  Royal Avenue');
INSERT INTO customer VALUES('514-202-1544','Ava Pollard','1120  th Avenue');
INSERT INTO customer VALUES('514-303-2334','Teegan Liu','3417  Brew Creek Rd');
INSERT INTO customer VALUES('514-404-2154','Jing Guo','283  Jasper Avenue');
INSERT INTO customer VALUES('514-273-3594','Rong Huang','3242  Boulevard Cremazie');

-- Staff
INSERT INTO staff VALUES(1,'Samuel Randall', 'morning',12.0); 
INSERT INTO staff VALUES(2,'Shala Tang', 'morning',12.0); 
INSERT INTO staff VALUES(3,'Macie Finely', 'afternoon',12.0); 
INSERT INTO staff VALUES(4,'Jeffrey Frye', 'afternoon',12.0); 
INSERT INTO staff VALUES(5,'Anna Black', 'evening',12.0); 
INSERT INTO staff VALUES(6,'Hugo Turner', 'evening',12.0); 
INSERT INTO staff VALUES(7,'Annie Carr', 'morning',13.0); 
INSERT INTO staff VALUES(8,'Cara Allenr', 'morning',13.0); 
INSERT INTO staff VALUES(9,'Jodie Stuart', 'afternoon',13.0); 
INSERT INTO staff VALUES(10,'Cassie Noble', 'afternoon',13.0); 
INSERT INTO staff VALUES(11,'Lachlan Lawrence', 'evening',13.0); 
INSERT INTO staff VALUES(12,'Ammie Summers', 'evening',13.0);
INSERT INTO staff VALUES(13,'Lacie Barr', 'morning',15.0);
INSERT INTO staff VALUES(14,'Isla Greer', 'morning',15.0);
INSERT INTO staff VALUES(15,'Meadow Moran', 'afternoon',15.0);
INSERT INTO staff VALUES(16,'Meadow Mawwan', 'afternoon',15.0);
INSERT INTO staff VALUES(17,'Luis Nash', 'evening',15.0);
INSERT INTO staff VALUES(18,'Melanie Hill', 'evening',15.0);

-- Platform
INSERT INTO platform VALUES('Uber Eats', 'https://www.ubereats.com/en-CA');
INSERT INTO platform VALUES('Skip The Dishes', 'https://www.skipthedishes.com/');
INSERT INTO platform VALUES('Foodora','https://www.foodora.ca/');
INSERT INTO platform VALUES('Food Highway','https://www.foodhwy.com/');
INSERT INTO platform VALUES('Fantuan Delivery','https://en.fantuan.ca/');

-- Orders
INSERT INTO orders VALUES( '001', 2.1);
INSERT INTO orders VALUES( '002', 1.5);
INSERT INTO orders VALUES( '003', 1.2);
INSERT INTO orders VALUES( '004', 3.0);
INSERT INTO orders VALUES( '005', 2.3);
INSERT INTO orders VALUES( '006', 4.0);
INSERT INTO orders VALUES( '007', 3.7);
INSERT INTO orders VALUES( '008', 2.1);
INSERT INTO orders VALUES( '009', 2.4);
INSERT INTO orders VALUES( '010', 3.5);
INSERT INTO orders VALUES( '011', 4.1);
INSERT INTO orders VALUES( '012', 1.7);
INSERT INTO orders VALUES( '013', 2.3);
INSERT INTO orders VALUES( '014', 2.4);
INSERT INTO orders VALUES( '015', 5.5);

-- Dine_in_orders
INSERT INTO dine_in_orders VALUES('001', '514-123-8900');
INSERT INTO dine_in_orders VALUES('002', '514-421-1768');
INSERT INTO dine_in_orders VALUES('003', '514-141-5854');
INSERT INTO dine_in_orders VALUES('004', '514-133-1567');
INSERT INTO dine_in_orders VALUES('005', '514-140-1264');
INSERT INTO dine_in_orders VALUES('010', '514-123-8900');
INSERT INTO dine_in_orders VALUES('011', '514-404-2154');
INSERT INTO dine_in_orders VALUES('012', '514-273-3594');
INSERT INTO dine_in_orders VALUES('013', '514-273-3594');

-- Delivery_orders
INSERT INTO delivery_orders VALUES( '006', '514-202-1544', 7,  'Uber Eats', 5.0);
INSERT INTO delivery_orders VALUES( '007', '514-303-2334', 9,  'Skip The Dishes', 3.0);
INSERT INTO delivery_orders VALUES( '008', '514-404-2154', 10,  'Foodora', 2.5);
INSERT INTO delivery_orders VALUES( '009', '514-273-3594', 9,  'Food Highway', 2.7);
INSERT INTO delivery_orders VALUES( '014', '514-273-3594', 10,  'Food Highway', 2.7);
INSERT INTO delivery_orders VALUES( '015', '514-303-2334', 8,  'Foodora', 2.5);

-- Waiter
INSERT INTO waiter VALUES(1); 
INSERT INTO waiter VALUES(2); 
INSERT INTO waiter VALUES(3); 
INSERT INTO waiter VALUES(4); 
INSERT INTO waiter VALUES(5); 
INSERT INTO waiter VALUES(6); 

-- Delivery_guy
INSERT INTO delivery_guy VALUES('Car','514-999-9999',7); 
INSERT INTO delivery_guy VALUES('Bike', '514-888-8888',8); 
INSERT INTO delivery_guy VALUES('On foot', '514-777-7777',9); 
INSERT INTO delivery_guy VALUES('Car', '514-666-6666',10); 
INSERT INTO delivery_guy VALUES('Bike', '514-555-5555',11); 
INSERT INTO delivery_guy VALUES('On foot', '514-444-4444',12);

-- Chef
INSERT INTO chef VALUES(2, 'Cutting',13);
INSERT INTO chef VALUES(3, 'Steaming',14);
INSERT INTO chef VALUES(4, 'Braising',15);
INSERT INTO chef VALUES(4, 'Braising',16);
INSERT INTO chef VALUES(3, 'Stir-frying',17);
INSERT INTO chef VALUES(5, 'Stir-frying',18);

-- Dish
INSERT INTO dish VALUES( 'Tenderized Truffles & Yak', 50.99);
INSERT INTO dish VALUES( 'Sautéed Dark Beer Pork', 28.9);
INSERT INTO dish VALUES( 'Tenderized Parmesan Lobster', 99.9);
INSERT INTO dish VALUES( 'Simmered Peas & Mushroom Oysters', 66.6);
INSERT INTO dish VALUES( 'Breaded Cucumber & Lime Pizza', 19.9);
INSERT INTO dish VALUES( 'Roasted Almonds & Avocado Bread', 28.9);
INSERT INTO dish VALUES( 'Rum and Praline Delight', 17.9);
INSERT INTO dish VALUES( 'Chestnut and Nutmeg Gingerbread', 25.9);
INSERT INTO dish VALUES( 'Ginger Candy',5.99);
INSERT INTO dish VALUES( 'Cranberry Genoise', 27);
INSERT INTO dish VALUES( 'Fire-Roasted Basil & Mint Yak', 32.3);
INSERT INTO dish VALUES( 'Simmered Mountain Rabbit', 22.5);
INSERT INTO dish VALUES( 'Pressure-Fried Vegetables & Frog', 31.5);
INSERT INTO dish VALUES( 'Sautéed Orange & Mustard Vegetables', 26.9);
INSERT INTO dish VALUES( 'Barbecued Mustard & Garlic Calzone', 25);
INSERT INTO dish VALUES( 'boiled spicy fish', 30.99);
INSERT INTO dish VALUES( 'Guoyou pork', 17.99);
INSERT INTO dish VALUES( 'Sweet and sour pork ribs', 14.5);
INSERT INTO dish VALUES( 'beef pho',10.5);
INSERT INTO dish VALUES( 'General Tsos Chicken',15.5);

-- Reservation
INSERT INTO reservation VALUES( '2020-01-29','514-123-8900','19:30');
INSERT INTO reservation VALUES( '2020-01-30','514-421-1768','16:30');
INSERT INTO reservation VALUES( '2020-01-21','514-141-5854','17:25');
INSERT INTO reservation VALUES( '2020-01-22','514-133-1567','16:45');
INSERT INTO reservation VALUES( '2020-01-23','514-140-1264','16:10');
INSERT INTO reservation VALUES( '2020-01-15','514-202-1544','18:20');
INSERT INTO reservation VALUES( '2020-02-19','514-303-2334','18:10');
INSERT INTO reservation VALUES( '2020-01-22','514-404-2154','12:30');
INSERT INTO reservation VALUES( '2020-01-23','514-273-3594','11:45');
INSERT INTO reservation VALUES( '2020-01-25','514-421-1768','12:20');

-- Contain
INSERT INTO contain VALUES('001', 'Tenderized Truffles & Yak',2);
INSERT INTO contain VALUES('001', 'Guoyou pork', 1);
INSERT INTO contain VALUES('001', 'General Tsos Chicken',2);
INSERT INTO contain VALUES('002', 'boiled spicy fish', 2);
INSERT INTO contain VALUES('002', 'beef pho',2);
INSERT INTO contain VALUES('002', 'Barbecued Mustard & Garlic Calzone', 1);
INSERT INTO contain VALUES('003', 'Pressure-Fried Vegetables & Frog',1);
INSERT INTO contain VALUES('003', 'Chestnut and Nutmeg Gingerbread', 2);
INSERT INTO contain VALUES('003', 'Tenderized Parmesan Lobster',1);
INSERT INTO contain VALUES('004', 'Tenderized Truffles & Yak',1);
INSERT INTO contain VALUES('004', 'Simmered Mountain Rabbit',2);
INSERT INTO contain VALUES('005', 'Tenderized Truffles & Yak',2);
INSERT INTO contain VALUES('005', 'Guoyou pork', 1);
INSERT INTO contain VALUES('005', 'Sweet and sour pork ribs',2);
INSERT INTO contain VALUES('006', 'boiled spicy fish', 1);
INSERT INTO contain VALUES('006', 'Cranberry Genoise', 2);
INSERT INTO contain VALUES('006', 'Barbecued Mustard & Garlic Calzone', 1);
INSERT INTO contain VALUES('007', 'Pressure-Fried Vegetables & Frog',1);
INSERT INTO contain VALUES('007', 'Chestnut and Nutmeg Gingerbread', 2);
INSERT INTO contain VALUES('008', 'Breaded Cucumber & Lime Pizza',2);
INSERT INTO contain VALUES('008', 'Tenderized Truffles & Yak',1);
INSERT INTO contain VALUES('009', 'Simmered Mountain Rabbit',2);
INSERT INTO contain VALUES('010', 'Sautéed Orange & Mustard Vegetables', 1);
INSERT INTO contain VALUES('010', 'Pressure-Fried Vegetables & Frog',1);
INSERT INTO contain VALUES('010', 'Chestnut and Nutmeg Gingerbread', 2);
INSERT INTO contain VALUES('014', 'Tenderized Parmesan Lobster',1);
INSERT INTO contain VALUES('012', 'Fire-Roasted Basil & Mint Yak',1);
INSERT INTO contain VALUES('013', 'Breaded Cucumber & Lime Pizza',1);
INSERT INTO contain VALUES('011', 'Tenderized Parmesan Lobster',1);
INSERT INTO contain VALUES('012', 'Rum and Praline Delight',1);
INSERT INTO contain VALUES('013', 'Sautéed Dark Beer Pork',1);
INSERT INTO contain VALUES('014', 'Ginger Candy',1);
INSERT INTO contain VALUES('015', 'Tenderized Parmesan Lobster',1);
INSERT INTO contain VALUES('015', 'beef pho',1);

-- Cooked_by
INSERT INTO cooked_by VALUES( 'Tenderized Truffles & Yak', 13);
INSERT INTO cooked_by VALUES( 'Sautéed Dark Beer Pork', 14);
INSERT INTO cooked_by VALUES( 'Tenderized Parmesan Lobster', 15);
INSERT INTO cooked_by VALUES( 'Simmered Peas & Mushroom Oysters', 16);
INSERT INTO cooked_by VALUES( 'Breaded Cucumber & Lime Pizza', 17);
INSERT INTO cooked_by VALUES( 'Roasted Almonds & Avocado Bread', 18);
INSERT INTO cooked_by VALUES( 'Rum and Praline Delight', 13);
INSERT INTO cooked_by VALUES( 'Chestnut and Nutmeg Gingerbread', 14);
INSERT INTO cooked_by VALUES( 'Ginger Candy',15);
INSERT INTO cooked_by VALUES( 'Cranberry Genoise', 16);
INSERT INTO cooked_by VALUES( 'Fire-Roasted Basil & Mint Yak', 17);
INSERT INTO cooked_by VALUES( 'Simmered Mountain Rabbit', 18);
INSERT INTO cooked_by VALUES( 'Pressure-Fried Vegetables & Frog', 13);
INSERT INTO cooked_by VALUES( 'Sautéed Orange & Mustard Vegetables', 14);
INSERT INTO cooked_by VALUES( 'Barbecued Mustard & Garlic Calzone', 15);
INSERT INTO cooked_by VALUES( 'boiled spicy fish', 16);
INSERT INTO cooked_by VALUES( 'Guoyou pork', 17);
INSERT INTO cooked_by VALUES( 'Sweet and sour pork ribs', 18);
INSERT INTO cooked_by VALUES( 'beef pho',13);
INSERT INTO cooked_by VALUES( 'General Tsos Chicken',14);

-- served_by
INSERT INTO served_by VALUES('001',1);
INSERT INTO served_by VALUES('002',2);
INSERT INTO served_by VALUES('003',3);
INSERT INTO served_by VALUES('004',4);
INSERT INTO served_by VALUES('005',5);
INSERT INTO served_by VALUES('006',6);