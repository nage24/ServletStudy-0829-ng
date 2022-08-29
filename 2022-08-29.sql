/*
여러 줄 주석
	DML
	CRUD
	C = insert
	R = select
	
	U = update
	D = delete
*/
# 한 줄 주석
# INSERT
INSERT INTO
	user_mst
		(user_code, 
		user_id, 
		user_password) 
		VALUES(
		0, 
		'junil4', 
		'1234');
		
# 데이터 여러 개를 한번에 insert 하는 방법
INSERT INTO
	user_mst
		VALUES(
		0, 
		'junil5', 
		'1234'),
		(
		0, 
		'junil6', 
		'1234'),
		(
		0, 
		'junil7', 
		'1234');
		
# SELECT
SELECT 
	user_id
FROM
	user_mst
WHERE
#user_code > 3 
#AND user_code < 6
#user_id = 'junil';
#user_id LIKE 'junil%';
user_id IN ('junil3', 'junil4');
OR user_code < 6;

# where 서브쿼리
	user_id IN(
		SELECT 
			USER_id
		from
			user_mst
		where
			user_code > 2
			AND user_code < 6
		);
		
SELECT 
	*
FROM	
	user_mst;
	
# update
UPDATE
	user_mst
SET
	user_password = '1111',
	user_id = 'junil2222'
WHERE
	user_code = ( # where 절 이거 빼먹으면 안대겠죠???? 
		select
			user_code
		from
			user_mst
		where
			user_id = 'junil6'
		);
		
# delete

DELETE
FROM
	user_mst
WHERE
	user_code = 6; # 얘도 빼먹음 진자 클납니다 ㅡㅡ^