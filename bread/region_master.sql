CREATE SEQUENCE region_master_seq
INCREMENT BY 1
START WITH 1;



CREATE TABLE region_master (
	id NUMBER NOT NULL,
	region_name VARCHAR2(256)  NOT NULL ,
	country_id NUMBER NOT NULL,
	insert_date date ,
	update_date date ,
	CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country_master(id),
	PRIMARY KEY (id)
);

ALTER TABLE region_master ADD CONSTRAINT region_name_unique UNIQUE (region_name);



CREATE OR REPLACE TRIGGER rmid_insert_trigger
BEFORE INSERT ON region_master
FOR EACH ROW
BEGIN
  SELECT region_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;


create or replace trigger region_master_trigger
before insert or update on region_master
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

alter trigger region_master_trigger enable;


INSERT INTO region_master ( region_name, country_id ) VALUES ('ƒCƒ“ƒOƒ‰ƒ“ƒh', 23);
SELECT * FROM region_master;

update region_master set country_id = 211 where id = 1;
