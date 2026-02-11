CREATE SEQUENCE sandwich_ingredients_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE sandwich_ingredients (
	id NUMBER NOT NULL,
	sandwich_id NUMBER NOT NULL,
	ingredients_id NUMBER NOT NULL,
	insert_date date NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT fk_bread FOREIGN KEY (sandwich_id) REFERENCES sandwich_master(id),	CONSTRAINT fk_ingredients FOREIGN KEY (ingredients_id) REFERENCES ingredients_master(id),
	PRIMARY KEY (id)
);



CREATE OR REPLACE TRIGGER siid_insert_trigger
BEFORE INSERT ON sandwich_ingredients
FOR EACH ROW
BEGIN
  SELECT sandwich_ingredients_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger sandwich_ingredients_trigger
before insert or update on sandwich_ingredients
    for each row
begin

    if inserting then
        :new.insert_date :=localtimestamp;
        :new.update_date   :=localtimestamp;
    end if;
    if updating then
        :new.update_date   :=localtimestamp;
    end if;
end;

alter trigger sandwich_ingredients_trigger enable;

INSERT INTO sandwich_ingredients ( sandwich_id, ingredients_id ) VALUES (1, 1);

INSERT INTO sandwich_ingredients ( sandwich_id, ingredients_id ) VALUES (1, 2);

INSERT INTO sandwich_ingredients ( sandwich_id, ingredients_id ) VALUES (1, 3);

SELECT * FROM sandwich_ingredients, sandwich_master, ingredients_master
WHERE sandwich_ingredients.sandwich_id = sandwich_master.id
AND sandwich_ingredients.ingredients_id = ingredients_master.id;

