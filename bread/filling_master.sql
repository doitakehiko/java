CREATE SEQUENCE filling_master_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE filling_master (
	id NUMBER NOT NULL,
	filling_name VARCHAR2(256)  NOT NULL ,
	insert_date date NOT NULL ,
	update_date date NOT NULL ,
	PRIMARY KEY (id)
);
ALTER TABLE filling_master ADD CONSTRAINT filling_name_unique UNIQUE (filling_name);



CREATE OR REPLACE TRIGGER fmid_insert_trigger
BEFORE INSERT ON filling_master
FOR EACH ROW
BEGIN
  SELECT filling_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger filling_master_trigger
before insert or update on filling_master
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

alter trigger filling_master_trigger enable;
