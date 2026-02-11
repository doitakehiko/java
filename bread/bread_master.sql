CREATE SEQUENCE bread_seq
INCREMENT BY 1
START WITH 1;



CREATE TABLE bread_master (
	id NUMBER NOT NULL,
	filling_id ,
	sauce_id ,
	sandwich_id NOT NULL ,
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

INSERT INTO bread_master ( filling_id, sauce_id , sandwich_id, region_id, bread_name_id ) VALUES (1, NULL, 1, 1, 1);


UPDATE bread_master SET sauce_id = 1 WHERE id = 1;

SELECT bread_master.id, filling_master.filling_name, sauce_master.sauce_name, sandwich_master.sandwich_name, region_master.region_name, bread_name_master.bread_name, country_master.country_name_jp, country_master.country_name_en, country_code FROM bread_master, filling_master, sauce_master, sandwich_master, region_master, bread_name_master, country_master, country_code_master
WHERE 	bread_master.filling_id = filling_master.id(+)
	AND bread_master.sauce_id = sauce_master.id(+)
	AND bread_master.sandwich_id = sandwich_master.id
	AND bread_master.region_id = region_master.id
	AND region_master.country_id = country_master.id
	AND country_master.id = country_code_master.country_master_id
	AND bread_master.bread_name_id = bread_name_master.id;

