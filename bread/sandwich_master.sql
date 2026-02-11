CREATE SEQUENCE sandwich_master_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE sandwich_master (
	id NUMBER NOT NULL,
	sandwich_name VARCHAR2(256)  NOT NULL ,
	type_id  NUMBER ,
	insert_date date  NOT NULL,
	update_date date  NOT NULL,
	CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES type_master(id),
	PRIMARY KEY (id)
);


ALTER TABLE sandwich_master ADD CONSTRAINT sandwich_name_unique UNIQUE (sandwich_name);


CREATE OR REPLACE TRIGGER smid_insert_trigger
BEFORE INSERT ON sandwich_master
FOR EACH ROW
BEGIN
  SELECT sandwich_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;


create or replace trigger sandwich_master_trigger
before insert or update on sandwich_master
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

alter trigger sandwich_master_trigger enable;


INSERT INTO sandwich_master ( sandwich_name, type_id ) VALUES ('ヨークシャー・プディング・スモークサーモン・オープンサンドイッチ', 1);
SELECT * FROM sandwich_master;
