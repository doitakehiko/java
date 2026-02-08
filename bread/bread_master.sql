CREATE SEQUENCE bread_seq
INCREMENT BY 1
START WITH 1;



CREATE TABLE bread_master (
	id NUMBER NOT NULL,
	filling_id ,
	sauce_id ,
	sandwich_id ,
	region_id  NOT NULL ,
	bread_name_id  NOT NULL ,
	insert_date date ,
	update_date date ,
	CONSTRAINT fk_filling FOREIGN KEY (filling_id) REFERENCES filling_master(id),
	CONSTRAINT fk_sauce FOREIGN KEY (sauce_id) REFERENCES sauce_master(id),
	CONSTRAINT fk_sandwich FOREIGN KEY (sandwich_id) REFERENCES sandwich_master(id),
	CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region_master(id),
	CONSTRAINT fk_bread_name_master FOREIGN KEY (bread_name_id) REFERENCES bread_name_master(id),
	CONSTRAINT uk_bread_master UNIQUE (filling_id ,	sauce_id ,sandwich_id ,region_id, bread_name_id),
	PRIMARY KEY (id)
);


CREATE OR REPLACE TRIGGER bread_insert_trigger
BEFORE INSERT ON bread_master
FOR EACH ROW
BEGIN
  SELECT bread_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger bread_trigger
before insert or update on bread_master
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

alter trigger bread_trigger enable;
