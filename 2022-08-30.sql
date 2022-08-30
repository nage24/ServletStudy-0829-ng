#SELECT COUNT(artistid) FROM artists;
#SELECT DISTINCT count(artistId) FROM albums;
#SELECT albums.Title, artists.name 
#	FROM albums
#	left JOIN artists ON  albums.ArtistId = artists.ArtistId;
#SELECT Firstname, City FROM customers
#	WHERE city IN ( 'Oslo', 'Berlin');
#SELECT FirstName, City FROM customers
#	WHERE city LIKE 'V%';
	
#SELECT FirstName, City, FROM customers
#	WHERE Firstname LIKE 'A_____';

INSERT INTO product_mst(product_name, product_category, product_price)
	VALUES(
		'스타벅스 여름 한정 텀블러',
		1,
		34000);

UPDATE product_mst
SET product_price = product_price + 10000
WHERE product_mst.product_category = 1;

SELECT *
FROM product_mst;

UPDATE product_mst
SET product_price = product_price - 10000
WHERE product_mst.product_category = 1;

INSERT INTO category_mst(category_name)
		VALUES('텀블러'), 
				('머그컵'),
				('음료');
				
SELECT *
FROM category_mst;

SELECT *
FROM product_mst p, category_mst c
WHERE p.product_category = c.category_code;

SELECT
	pm.product_code,
	pm.product_name,
	pm.product_category,
	cm.category_name,
	pm.product_price
FROM
	product_mst pm
	LEFT OUTER JOIN category_mst cm ON(cm.category_code = pm.product_category);


SELECT
	pm.product_code,
	pm.product_name,
	pm.product_category,
	cm.category_name,
	pm.product_price
FROM
	product_mst pm
	LEFT OUTER JOIN category_mst cm ON(1=1); # join 테이블 on(조건)
	
INSERT INTO order_mst
	VALUES(1,
			1,
			1,
			NOW()),
			(2,
			2,
			4,
			NOW()),
			(3,
			3,
			2,
			NOW()),
			(4,
			1,
			3,
			NOW()),
			(5,
			2,
			5,
			NOW()),
			(6,
			1,
			5,
			NOW());
			
SELECT *
FROM order_mst;

SELECT *
FROM order_mst o
	LEFT OUTER JOIN user_mst u ON(u.user_code = o.order_user)
	LEFT OUTER JOIN product_mst p ON(p.product_code = o.order_product)
	LEFT OUTER JOIN category_mst c ON(c.category_code = p.product_category);
	
SELECT SUM(p.product_price) AS sumprice
FROM order_mst o
	LEFT OUTER JOIN user_mst u ON(u.user_code = o.order_user)
	LEFT OUTER JOIN product_mst p ON(p.product_code = o.order_product)
	LEFT OUTER JOIN category_mst c ON(c.category_code = p.product_category);

SELECT o.order_code, o.order_user, u.user_id, o.order_product, p.product_name, p.product_category, c.category_name, p.product_price, o.order_datetime
FROM order_mst o
	LEFT OUTER JOIN user_mst u ON(u.user_code = o.order_user)
	LEFT OUTER JOIN product_mst p ON(p.product_code = o.order_product)
	LEFT OUTER JOIN category_mst c ON(c.category_code = p.product_category);
	
WHERE
	o.order_code > 0
	AND p.product_price > 20000;
	
INSERT INTO order_mst
	VALUE(
		0, # 자동으로 8이 됐다 ... 
		4,
		9, # 값이 없는걸 넣어봄 -> order 쪽으로 left outer join 하면 없는 값은 null
		NOW()
	);
	
SELECT o.order_code, o.order_user, u.user_id, o.order_product, p.product_name, p.product_category, c.category_name, p.product_price, o.order_datetime
FROM order_mst o
	inner JOIN user_mst u ON(u.user_code = o.order_user)
	inner JOIN product_mst p ON(p.product_code = o.order_product)
	inner JOIN category_mst c ON(c.category_code = p.product_category);
	
INSERT
INTO
	user_mst(user_id, user_password)
	VALUES(
	'triggertest',
	1111);
	
DELETE
FROM
	user_mst
WHERE
	user_code = 9;
	
