CREATE OR REPLACE VIEW vMasterId AS
SELECT 
	bread_master.id ,
	filling_master.id fillingid,
	sauce_master.id sauceid, 
	sandwich_master.id sandwichid, 
	region_master.id regionid,
	bread_name_master.id bread_nameid
FROM 
	bread_master, 
	filling_master, 
	sauce_master, 
	sandwich_master, 
	region_master, 
	bread_name_master
WHERE 	
	bread_master.filling_id = filling_master.id(+)
	AND bread_master.sauce_id = sauce_master.id(+)
	AND bread_master.sandwich_id = sandwich_master.id
	AND bread_master.region_id = region_master.id
	AND bread_master.bread_name_id = bread_name_master.id;


SELECT * FROM vMasterId WHERE id = 1;