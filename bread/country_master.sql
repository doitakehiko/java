CREATE SEQUENCE country_master_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE country_master (
	id NUMBER NOT NULL,
	country_name_jp VARCHAR2(100)  NOT NULL ,
	country_name_en VARCHAR2(50)  NOT NULL ,
	insert_date date NOT NULL ,
	update_date date NOT NULL ,
	PRIMARY KEY (id)
);
ALTER TABLE country_master ADD CONSTRAINT country_name_jp_unique UNIQUE (country_name_jp);
ALTER TABLE country_master ADD CONSTRAINT country_name_en_unique UNIQUE (country_name_en);


CREATE OR REPLACE TRIGGER cm_id_insert_trigger
BEFORE INSERT ON country_master
FOR EACH ROW
BEGIN
  SELECT country_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger country_master_trigger
before insert or update on country_master
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

ALTER TRIGGER cm_id_insert_trigger ENABLE;
ALTER TRIGGER country_master_trigger ENABLE;

