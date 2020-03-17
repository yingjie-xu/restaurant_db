# Deliverable 3 (Group 62)

## Q1: Stored procedure

### Description: 

This function (Procedure) will train all the chef (i.e. increase the proficiency of the chef by one) below the function input `max_proficiency`. The function will take one input which is `max_proficiency`. All the chef with proficiency less than or equal to this `max_proficiency` will be trained. 

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

### Testing (sample execution)

- Query before execution

```
cs421=> SELECT sid, proficiency FROM chef ORDER BY sid;
 sid | proficiency
-----+-------------
  13 |           2    <-- will be trained
  14 |           3    <-- will be trained
  15 |           3    <-- will be trained
  16 |           3    <-- will be trained
  17 |           4
  18 |           5
(6 rows)
```

- Execution of the procedure (Nothing is returned because the return type is void)
  - We want to train all the chef will proficiency less than or equal to 3

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

