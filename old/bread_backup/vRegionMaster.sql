CREATE OR REPLACE VIEW vRegionMaster AS
SELECT 
	region_master.id , 
	region_master.region_name ,
	country_name_jp ,
	country_name_en ,
	vCountry.id cid
FROM 
	region_master , vCountry 
WHERE 
	region_master.country_id = vCountry.id;
