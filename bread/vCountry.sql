CREATE OR REPLACE VIEW vCountry AS
SELECT 
	ccm.id , 
	cm.country_name_jp ,
	cm.country_name_en ,
	ccm.country_code
FROM 
	country_master cm , country_code_master ccm
WHERE 
	cm.id = ccm.country_master_id;



select * from vCountry where country_name_jp = '日本';
select * from vCountry where country_name_en = 'Japan';
select * from vCountry where country_code = 81;

