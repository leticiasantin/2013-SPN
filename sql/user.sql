--insert
INSERT INTO spn.user(name, login, password, dt_of_birth, city, state, street, number, complements, neighborhood, zip_code)
		 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id;

--read
SELECT * FROM spn.user WHERE user_id = ? LIMIT 1;

--update
UPDATE spn.user SET name=?, login=?, password=?, dt_of_birth=?, city=?, state=?, street=?, number=?, 
			complements=?, neighborhood=?, zip_code=?  WHERE user_id = ?;


--deleteByUser
  String query = UPDATE spn.user SET status=false WHERE user_id = ?;

--userExistsById
  String query = SELECT user_id FROM spn.user WHERE user_id = ? LIMIT 1;

--userExists
   String query = SELECT user_id FROM spn.user WHERE login = ? AND password=? LIMIT 1;

--readByLogin
        String query = SELECT * FROM spn.user WHERE login = ?;





