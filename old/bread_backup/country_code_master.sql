CREATE SEQUENCE country_code_master_seq
INCREMENT BY 1
START WITH 1;


CREATE TABLE country_code_master (
	id NUMBER NOT NULL,
	country_master_id NUMBER NOT NULL,
	country_code NUMBER(5, 0) NOT NULL,
	insert_date date  NOT NULL ,
	update_date date  NOT NULL ,
	PRIMARY KEY (id),
	CONSTRAINT fk_country_code FOREIGN KEY (country_master_id) REFERENCES country_master(id),
	CONSTRAINT uk_country UNIQUE (country_master_id, country_code)
);



CREATE OR REPLACE TRIGGER ccd_id_insert_trigger
BEFORE INSERT ON country_code_master
FOR EACH ROW
BEGIN
  SELECT country_code_master_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

create or replace trigger country_code_master_trigger
before insert or update on country_code_master
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

alter trigger country_code_master_trigger enable;
SELECT * FROM country_code_master;

INSERT INTO country_code_master (country_master_id, country_code) SELECT id, :code FROM country_master WHERE country_name_jp = :namejp AND country_name_en = :nameen