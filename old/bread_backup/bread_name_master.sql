CREATE SEQUENCE bread_name_seq
INCREMENT BY 1
START WITH 1;



CREATE TABLE bread_name_master (
	id NUMBER NOT NULL,
	bread_name VARCHAR2(256)  NOT NULL ,
	insert_date date  NOT NULL ,
	update_date date  NOT NULL ,
	PRIMARY KEY (id)
);


ALTER TABLE bread_name_master ADD CONSTRAINT bread_name_unique UNIQUE (bread_name);


CREATE OR REPLACE TRIGGER bnid_insert_trigger
BEFORE INSERT ON bread_name_master
FOR EACH ROW
BEGIN
  SELECT bread_name_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger bread_name_trigger
before insert or update on bread_name_master
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

alter trigger bread_name_trigger enable;


INSERT INTO bread_name_master ( bread_name ) VALUES ('ヨークシャー・プディング');
SELECT * FROM bread_name_master;
