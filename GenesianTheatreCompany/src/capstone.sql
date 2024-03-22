DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Shows;

CREATE TABLE Admins (
    aid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    pwd VARCHAR(255)
);
INSERT INTO Admins (username, pwd) VALUES 
('admin', 'admin'),
('root', 'root'),
('srh', 'srh');

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
FROM 'capston/2024.csv' -- Your path
DELIMITER ','
CSV HEADER;
SELECT * FROM shows; 



