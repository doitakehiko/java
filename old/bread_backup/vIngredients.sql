CREATE OR REPLACE VIEW vIngredients AS
SELECT 
	ingredients_master.id id, 
	sandwich_master.sandwich_name ,
	ingredients_master.ingredients_name, 
	sandwich_ingredients.sandwich_id sid, 
	sandwich_ingredients.ingredients_id iid,
	sandwich_ingredients.id siid
FROM 
	sandwich_ingredients , sandwich_master, ingredients_master
WHERE 
	sandwich_ingredients.sandwich_id = sandwich_master.id
	AND sandwich_ingredients.ingredients_id = ingredients_master.id;

SELECT * FROM vIngredients WHERE sid = :id;