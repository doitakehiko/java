CREATE OR REPLACE VIEW vSandwich AS
SELECT 
	sandwich_master.id , 
	sandwich_master.sandwich_name ,
	type_master.id typeid,
	type_master.type_name
FROM 
	sandwich_master, type_master 
WHERE 
	sandwich_master.type_id = type_master.id(+);

SELECT * FROM vSandwich;