CREATE SEQUENCE type_master_seq
INCREMENT BY 1
START WITH 1;



CREATE TABLE type_master (
	id NUMBER NOT NULL,
	type_name VARCHAR2(256)  NOT NULL ,
	insert_date date ,
	update_date date ,
	PRIMARY KEY ( id )
);
ALTER TABLE type_master ADD CONSTRAINT type_name_unique UNIQUE (type_name);



CREATE OR REPLACE TRIGGER tmid_insert_trigger
BEFORE INSERT ON type_master
FOR EACH ROW
BEGIN
  SELECT type_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger type_master_trigger
before insert or update on type_master
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

alter trigger type_master_trigger enable;
