CREATE TABLE health_management (
 id int NOT NULL, 
 record_date DATE  NOT NULL,
 weight NUMBER(3, 1)  NOT NULL,
 body_fat NUMBER(3, 1)  NOT NULL,
 skeletal_muscle  NUMBER(3, 1)  NOT NULL,
 PRIMARY KEY (id)
);


CREATE OR REPLACE TRIGGER health_management_trigger
BEFORE INSERT ON health_management
FOR EACH ROW
BEGIN
  SELECT health_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/
