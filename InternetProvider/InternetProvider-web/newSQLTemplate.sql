SELECT * FROM (
  SELECT ROWNUM rownumber, sub.*
  FROM
    (SELECT * FROM tasks 
    WHERE role_id = 2
    ORDER BY ID DESC) sub
     WHERE ROWNUM <= 50)
WHERE rownumber > 0;