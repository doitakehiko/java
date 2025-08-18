CREATE SEQUENCE postal_codes_seq
INCREMENT BY 1
START WITH 1;

CREATE TABLE postal_codes (
	id NUMBER NOT NULL,
	postal_code CHAR(7) NOT NULL ,
	prefecture_name_kana VARCHAR2(18)  NOT NULL ,
	city_name_kana VARCHAR2(57) NOT NULL ,
	town_name_kana VARCHAR2(906)  NOT NULL ,
	prefecture_name VARCHAR2(12) NOT NULL ,
	city_name VARCHAR2(30) NOT NULL ,
	town_name VARCHAR2(891) NOT NULL ,
	PRIMARY KEY (id)
);



CREATE OR REPLACE TRIGGER postal_codes_trigger
BEFORE INSERT ON postal_codes
FOR EACH ROW
BEGIN
  SELECT postal_codes_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/
