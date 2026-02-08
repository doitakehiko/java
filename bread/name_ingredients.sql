CREATE SEQUENCE name_ingredients_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE name_ingredients (
	id NUMBER NOT NULL,
	bread_id NUMBER NOT NULL,
	ingredients_id NUMBER NOT NULL,
	insert_date date NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT fk_bread FOREIGN KEY (bread_id) REFERENCES bread_name_master(id),	CONSTRAINT fk_ingredients FOREIGN KEY (ingredients_id) REFERENCES ingredients_master(id),
	PRIMARY KEY (id)
);



CREATE OR REPLACE TRIGGER niid_insert_trigger
BEFORE INSERT ON name_ingredients
FOR EACH ROW
BEGIN
  SELECT name_ingredients_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger name_ingredients_trigger
before insert or update on name_ingredients
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

alter trigger name_ingredients_trigger enable;
