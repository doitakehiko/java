CREATE OR REPLACE VIEW vMaster AS
SELECT 
	bread_master.id, 
	filling_master.filling_name, 
	sauce_master.sauce_name, 
	sandwich_master.sandwich_name, 
	region_master.region_name, 
	bread_name_master.bread_name, 
	country_master.country_name_jp, 
	country_master.country_name_en, 
	country_code, 
	filling_master.id fillingid,
	sauce_master.id sauceid, 
	sandwich_master.id sandwichid, 
	bread_name_master.id bread_nameid,
	region_master.id regionid
FROM 
	bread_master, 
	filling_master, 
	sauce_master, 
	sandwich_master, 
	region_master, 
	bread_name_master, 
	country_master, 
	country_code_master
WHERE 	bread_master.filling_id = filling_master.id(+)
	AND bread_master.sauce_id = sauce_master.id(+)
	AND bread_master.sandwich_id = sandwich_master.id
	AND bread_master.region_id = region_master.id
	AND region_master.country_id = country_master.id
	AND country_master.id = country_code_master.country_master_id
	AND bread_master.bread_name_id = bread_name_master.id;



SELECT fillingid FROM vMaster WHERE id = 1;


