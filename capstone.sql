DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Shows;

CREATE TABLE Admins (
    aid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    pwd VARCHAR(255),
	firstname VARCHAR(255),
    lastname VARCHAR(255),
	email VARCHAR(255),
	roles VARCHAR(255) CHECK (roles IN ('Administrator', 'Marketing', 'Manager', 'Member'))
);
INSERT INTO Admins (username, pwd, firstname, lastname, email, roles) VALUES 
('admin', 'admin','admin', 'a','no emial', 'Administrator'),
('root', 'root','Marketing','M','no email', 'Marketing'),
('srh', 'srh', 'rh', 's','srh@usyd.com','Member');


CREATE TABLE Shows (
    pid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pname VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
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
FROM '/Users/zeqianliu/Desktop/capston/2024.csv' -- Your path
DELIMITER ','
CSV HEADER;
SELECT * FROM Admins; 



