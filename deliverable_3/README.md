# Deliverable 3 (Group 62)

[TOC]

## Q1: Stored procedure

### Description: 

This function (Procedure) will train all the chef (i.e. increase the proficiency of the chef by one) below the function input `max_proficiency`. The function will take one input which is `max_proficiency`. All the chef with proficiency less than or equal to this `max_proficiency` will be trained. 

> Handling constraints: Since the proficiency of the chef is between 0 and 5, if the input `max_proficiency` is out of this range, no chef will be trained (i.e. the database remains unchanged)

### Procedure:

```sql
CREATE OR REPLACE FUNCTION train_all_the_chef(max_proficiency INTEGER)
RETURNS VOID AS $$
DECLARE
    rec_chef RECORD;
    cur_chefs CURSOR(max_proficiency INTEGER)
       FOR SELECT sid, proficiency 
       FROM chef
       WHERE proficiency <= max_proficiency;
BEGIN
   -- Open the cursor
    OPEN cur_chefs(max_proficiency);
 
	IF max_proficiency > 4 THEN
		RETURN;
	END IF;
	
    LOOP
        FETCH cur_chefs INTO rec_chef;
        EXIT WHEN NOT FOUND;
	UPDATE chef SET proficiency = proficiency + 1 WHERE sid = rec_chef.sid;
    END LOOP;
	
    -- Close the cursor
    CLOSE cur_chefs;
END;
$$ LANGUAGE plpgsql;


SELECT sid, proficiency FROM chef 

UPDATE chef 
SET proficiency = 2
WHERE sid = 13

SELECT FROM train_all_the_chef(3)
```

### Example (Test with valid input)

- Query before execution
  - We want to train all the chef with proficiency less than or equal to 3

```
cs421=> SELECT sid, proficiency FROM chef ORDER BY sid;
 sid | proficiency
-----+-------------
  13 |           2
  14 |           3
  15 |           3
  16 |           3
  17 |           4
  18 |           5
(6 rows)
```

- Execution of the procedure (Nothing is returned because the return type is void)

```
cs421=> SELECT FROM train_all_the_chef(3);
--
(1 row)
```

- Query after execution

```
cs421=> SELECT sid, proficiency FROM chef ORDER BY sid;
 sid | proficiency
-----+-------------
  13 |           3    <-- after training (increased by 1)
  14 |           4    <-- after training (increased by 1)
  15 |           4    <-- after training (increased by 1)
  16 |           4    <-- after training (increased by 1)
  17 |           4
  18 |           5
(6 rows)
```

### Example (Test with invalid input)

- Query before execution
  - We want to train all the chef with proficiency less than or equal to 6 (which is not a valid execution)
  - The expectation is that the table remains **unchanged**

```
cs421=> SELECT sid, proficiency FROM chef ORDER BY sid;
 sid | proficiency
-----+-------------
  13 |           2    <-- stay the same
  14 |           3    <-- stay the same
  15 |           3    <-- stay the same
  16 |           3    <-- stay the same
  17 |           4    <-- stay the same
  18 |           5    <-- stay the same
(6 rows)
```

- Execution of the procedure (Nothing is returned because the return type is void)

```
cs421=> SELECT FROM train_all_the_chef(6);
--
(1 row)
```

- Query after execution

```
cs421=> SELECT sid, proficiency FROM chef ORDER BY sid;
 sid | proficiency
-----+-------------
  13 |           2    <-- stay the same
  14 |           3    <-- stay the same
  15 |           3    <-- stay the same
  16 |           3    <-- stay the same
  17 |           4    <-- stay the same
  18 |           5    <-- stay the same
(6 rows)
```



## Q4: Visualization

We used Jupyter Notebook for this part of the deliverable.

[View our code and result graph on Google CoLab.](https://colab.research.google.com/drive/1N3ck6t2Ib-MlFnOCvvYOw3uwPriWEtpU)

