DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Shows;

CREATE TABLE Users (
    uid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    pwd VARCHAR(255),
	firstname VARCHAR(255),
    lastname VARCHAR(255),
	email VARCHAR(255),
	roles INT CHECK (roles IN (1, 2, 3, 4))
-- 	roles VARCHAR(255) CHECK (roles IN ('Administrator', 'Marketing', 'Manager', 'Member'))
);
INSERT INTO Users (username, pwd, firstname, lastname, email, roles) VALUES 
('admin', 'admin','admin', 'a','no emial', 1),
('root', 'root','Marketing','M','no email', 2),
('srh', 'srh', 'rh', 's','srh@usyd.com',4);

-- CREATE TABLE Members (
--     mid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
-- 	email VARCHAR(255),
--     pwd VARCHAR(255),
-- 	membername VARCHAR(255)
-- );

CREATE TABLE Shows (
    pid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pname VARCHAR(255) NOT NULL,
    subtitle TEXT,
    startdate date NOT NULL,
	enddate date,
    productions TEXT,
    casts TEXT,
    crews TEXT,
    contents TEXT,
    cover TEXT,
    stills TEXT
);

COPY Shows(pname, subtitle, startdate, enddate, productions, casts, crews, contents, cover, stills)
FROM '/Users/zeqianliu/Desktop/capston/datas.csv' -- Your path
DELIMITER ','
CSV HEADER;
SELECT * FROM Shows; 



