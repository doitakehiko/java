CREATE SEQUENCE sauce_master_seq
INCREMENT BY 1
START WITH 1;

CREATE TABLE sauce_master (
	id NUMBER NOT NULL,
	sauce_name VARCHAR2(256)  NOT NULL ,
	insert_date date  NOT NULL ,
	update_date date  NOT NULL ,
	PRIMARY KEY (id)
);

ALTER TABLE sauce_master ADD CONSTRAINT sauce_name_unique UNIQUE (sauce_name);


CREATE OR REPLACE TRIGGER sauceid_insert_trigger
BEFORE INSERT ON sauce_master
FOR EACH ROW
BEGIN
  SELECT sauce_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;


create or replace trigger sauce_master_trigger
before insert or update on sauce_master
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

alter trigger sauce_master_trigger enable;


INSERT INTO sauce_master ( sauce_name ) VALUES ('ヨークシャー・プディング・スモークサーモン・オープンサンドイッチのソース');
SELECT * FROM sauce_master;