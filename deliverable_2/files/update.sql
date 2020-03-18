-- raise the salary for chef who is the best for his/her cooking style
UPDATE staff
SET salary = salary+1
WHERE sid IN
(SELECT S.sid FROM staff S JOIN
(SELECT chef.sid, chef.proficiency,chef.cooking_style FROM chef
INNER JOIN
((SELECT t1.cooking_style, MAX(t1.proficiency) AS maxProficiency FROM
  (SELECT DISTINCT S.sname, S.sid, C.proficiency, C.cooking_style FROM staff S
  JOIN
  chef C ON C.sid = S.sid)t1
GROUP BY t1.cooking_style)) t2
ON chef.proficiency = t2.maxProficiency AND t2.cooking_style = chef.cooking_style)t3
ON S.sid = t3.sid);


-- raise the price for most popular dishes
UPDATE dish 
SET price = price * 1.1
WHERE dish_name IN 
(SELECT dish_name FROM contain GROUP BY dish_name
HAVING COUNT(*) = (
SELECT MAX(y.dcount) FROM 
(SELECT dish_name ,COUNT(*) AS dcount FROM contain GROUP BY dish_name) y));

