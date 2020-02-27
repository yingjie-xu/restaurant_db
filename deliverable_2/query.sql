/*
Query Description1:
List the dish_name of the dish which sold most, as well as the portion it sold
*/
SELECT dish_name ,COUNT(*) AS dcount FROM contain GROUP BY dish_name
HAVING COUNT(*) = (
SELECT MAX(y.dcount) FROM 
(SELECT dish_name ,COUNT(*) AS dcount FROM contain GROUP BY dish_name) y);

/* 
Query Description2:
Display information about all the delivery orders. 
The query will first show the order number, 
and then display the sid and name of the delivery guy. 
It will also show the customer's information to the corresponding order number.
*/

SELECT t2.order_number, s.sname AS delivery_guy_name, t2.sid AS delivery_guy_sid, 
	t2.delivery_guy_phone, t2.cus_name, t2.cus_phone, t2.cus_address
FROM staff s 
INNER JOIN
	(
		SELECT t1.order_number, t1.cus_name, t1.cus_phone, t1.cus_address,
			guy.phone_number AS delivery_guy_phone, t1.sid AS sid 
		FROM delivery_guy guy
		INNER JOIN 
		(
			SELECT cus.phone_number AS cus_phone, cus.address AS cus_address, 
				cus.canme AS cus_name, sid, order_number
			FROM customer cus INNER JOIN delivery_orders d
			ON cus.phone_number = d.phone_number
		) t1
		ON t1.sid = guy.sid
	) t2
ON s.sid = t2.sid;

/*
Query Description3: 
List all reservations ordered by revervation date, then by timeslot within each reservation date.
The derived table will display the names and phone numbers of those who 
have made an reservation, along with the reservation date and timeslot
*/

SELECT canme, cus.phone_number, rdate, timeslot 
FROM customer AS cus, reservation AS res
WHERE cus.phone_number = res.phone_number
ORDER BY rdate, timeslot;

/*
Query Description4: 
Select best chef for each of the cooking style
*/
SELECT S.sname, t3.proficiency, t3.cooking_style,S.sid FROM staff S JOIN
(SELECT chef.sid, chef.proficiency,chef.cooking_style FROM chef
INNER JOIN
((SELECT t1.cooking_style, MAX(t1.proficiency) AS maxProficiency FROM
  (SELECT DISTINCT S.sname, S.sid, C.proficiency, C.cooking_style FROM staff S
  JOIN
  chef C ON C.sid = S.sid)t1
GROUP BY t1.cooking_style)) t2
ON chef.proficiency = t2.maxProficiency AND t2.cooking_style = chef.cooking_style)t3
ON S.sid = t3.sid;

/* 
Query Description5: 
Total revenue for all orders
*/
SELECT SUM(D.price) AS total_revenue FROM dish D
JOIN
  (SELECT * FROM orders O
  JOIN
  contain C ON O.order_number = C.order_number)t1
ON D.dish_name = t1.dish_name;