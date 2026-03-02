CREATE SEQUENCE ingredients_master_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE ingredients_master (
	id NUMBER NOT NULL,
	ingredients_name VARCHAR2(256) NOT NULL ,
	insert_date date  NOT NULL ,
	update_date date  NOT NULL ,
	PRIMARY KEY (id)
);

ALTER TABLE ingredients_master ADD CONSTRAINT ingredients_name_unique UNIQUE (ingredients_name);


CREATE OR REPLACE TRIGGER imid_insert_trigger
BEFORE INSERT ON ingredients_master
FOR EACH ROW
BEGIN
  SELECT ingredients_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger ingredients_master_trigger
before insert or update on ingredients_master
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

alter trigger ingredients_master_trigger enable;


INSERT INTO ingredients_master ( ingredients_name ) VALUES ('ディル');
INSERT INTO ingredients_master ( ingredients_name ) VALUES ('サワークリーム');
INSERT INTO ingredients_master ( ingredients_name ) VALUES ('スモークサーモン');
SELECT * FROM ingredients_master;