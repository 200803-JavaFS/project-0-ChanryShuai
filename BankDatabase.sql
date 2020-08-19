DROP TABLE IF EXISTS accounts cascade;
DROP TABLE IF EXISTS users cascade;

CREATE TABLE users (
	userID SERIAL, 
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(30) NOT NULL,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	user_type VARCHAR(30) NOT NULL
);

CREATE TABLE accounts(
	accID SERIAL PRIMARY KEY,
	accBalance NUMERIC(38,2),
	accType VARCHAR(30),
	accStatus BOOLEAN,
	user_fk VARCHAR (30) REFERENCES users(username)
);

--TRUNCATE TABLE users cascade;
--TRUNCATE TABLE accounts;

	
INSERT INTO users (username, password, first_name, last_name, user_type)
	VALUES('sponge.pants@bikinibottom.com', 'my best friend patrick', 'Spongebob', 'Squarepants', 'client'),
	('mr.krabs@bikinibottom.com', 'i love money', 'Mr.', 'Krabs', 'administrator'),
	('plankton@bikinibottom.com', 'what is the secret formula', 'Plankton', 'Karen', 'client'),
	('squidward@bikinibottom.com', 'i hate everything', 'Squidward', 'Boring', 'employee'),
	('PabloPicasso@Cubists.com', 'better than mattise', 'Pablo', 'Picasso', 'administrator'),
	('LetMe@Pass.please', 'needtopass', 'Let Me', 'Pass', 'client'),
	('JohnDoe@lame.com', 'mypassword', 'John', 'Doe', 'employee'),
	('qianyi.shuai@revature.net', 'keep secret', 'Chanry', 'Shuai', 'client');

INSERT INTO accounts (accBalance, accType, accStatus, user_fk)
	VALUES ('0', 'checking', true, 'sponge.pants@bikinibottom.com'),
	('-100', 'saving', false, 'sponge.pants@bikinibottom.com'),
	('3000', 'saving', true, 'sponge.pants@bikinibottom.com'),
	('1e10', 'checking', TRUE, 'plankton@bikinibottom.com'),
	('00.000', 'checking', true, 'LetMe@Pass.please'),
	('00.00', 'saving', true, 'LetMe@Pass.please'),
	('1e10', 'saving', true, 'qianyi.shuai@revature.net'),
	('1e10', 'saving', true, 'qianyi.shuai@revature.net'),
	('-1e10', 'checking', false, 'qianyi.shuai@revature.net')
	;

CREATE OR REPLACE FUNCTION trigger_set_time() RETURNS TRIGGER 
AS $$
BEGIN 
	NEW.update_at = NOW();
	RETURN NEW;
END;
$$ LANGUAGE plpgsql; 

ALTER TABLE users ADD COLUMN update_at TIMESTAMP;
ALTER TABLE accounts ADD COLUMN update_at TIMESTAMP;

CREATE TRIGGER set_time BEFORE UPDATE ON users FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time();

CREATE TRIGGER set_time BEFORE UPDATE ON accounts FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time();


--SELECT * FROM users WHERE userID = 3;
--SELECT * FROM accounts WHERE user_fk = 'qianyi.shuai@revature.net';

