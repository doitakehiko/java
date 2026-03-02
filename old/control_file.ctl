LOAD DATA
INFILE 'import_utf_ken_all.csv'
REPLACE
INTO TABLE postal_codes
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
(
	postal_code, 
	prefecture_name_kana, 
	city_name_kana, 
	town_name_kana CHAR(906), 
	prefecture_name, 
	city_name, 
	town_name CHAR(891)
)