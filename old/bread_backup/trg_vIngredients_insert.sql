CREATE OR REPLACE TRIGGER trg_vIngredients_insert
INSTEAD OF INSERT ON vIngredients
FOR EACH ROW
BEGIN
  INSERT INTO sandwich_ingredients (sandwich_id, ingredients_id) VALUES (:new.sid, :new.iid);
END;
/

INSERT INTO vIngredients (sandwich_name , ingredients_name, sid, iid
) VALUES( 'TESTaa', 'TEaaST1', 1, 3); 


