SELECT
	cl.country_id,
	c.name,
	c.region_id,
	r.name,
	r.continent_id,
	cc.name,
	
	cl.language_id,
	l.language
	
FROM 
	country_languages cl
	LEFT OUTER JOIN countries c ON(c.country_id = cl.country_id)
	LEFT OUTER JOIN languages l ON(l.language_id = cl.language_id)
	LEFT OUTER JOIN regions r ON(r.region_id = c.region_id)
	LEFT OUTER JOIN continents cc ON(cc.continent_id = r.continent_id)

WHERE
	cl.country_id = 1;
	

SELECT
	cl.country_id,
	c.name,
	MAX(c.area) AS max_area,
	c.region_id,
	r.name,
	r.continent_id,
	cc.name,
	
	cl.language_id,
	l.language
	
FROM 
	country_languages cl
	LEFT OUTER JOIN countries c ON(c.country_id = cl.country_id)
	LEFT OUTER JOIN languages l ON(l.language_id = cl.language_id)
	LEFT OUTER JOIN regions r ON(r.region_id = c.region_id)
	LEFT OUTER JOIN continents cc ON(cc.continent_id = r.continent_id)

GROUP BY
	cl.country_id,
	c.name;