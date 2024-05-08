DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Shows;
DROP TABLE IF EXISTS Pages;


CREATE TABLE Users (
    uid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    pwd VARCHAR(255),
	firstname VARCHAR(255),
    lastname VARCHAR(255),
	email VARCHAR(255) UNIQUE,
	roles INT CHECK (roles IN (1, 2, 3, 4))
-- 	roles VARCHAR(255) CHECK (roles IN ('Administrator', 'Marketing', 'Manager', 'Member'))
);
INSERT INTO Users (username, pwd, firstname, lastname, email, roles) VALUES 
('admin', 'admin','admin', 'a','admin@usyd.com', 1),
('marketing', 'marketing','marketing','M','marketing@usyd.com', 2),
('manager', 'manager','manager','M','manager@usyd.com', 3),
('srh', 'srh', 'rh', 's','srh@usyd.com',4);

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

CREATE TABLE Reviews (
    rid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pid INT NOT NULL,
    reviewtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    commen TEXT NOT NULL,
    FOREIGN KEY (pid) REFERENCES Shows(pid)
);
INSERT INTO Reviews (pid, commen) VALUES 
(3, 'Good'),
(3, 'I love it'),
(3, 'Nice'),
(3, 'wanna see again!');

CREATE TABLE Pages (
    pgid INT PRIMARY KEY,
	pgname TEXT,
    title TEXT,  
    contents TEXT,        
    image TEXT    
);

INSERT INTO Pages (pgid, pgname, title, contents, image) VALUES 
(1, 'home/On stage now', 'Let\\s Kill Agatha Christie', 'In an hilarious take on the murder mystery genre, an unsuccessful writer decides to secure her own place as a literary legend by staging her own murder. She invites her three worst enemies for a weekend and gives them all a good reason to kill her. ', 'https://www.genesiantheatre.com.au/s2024/4/images/poster.jpg'),
(2, 'home/fundraising', 'Help us build our new home', 'We need your generous support\n In order to make our vision a reality, we cordially invite you to join us in supporting our projects by making a donation. Every donation you make is vital and will go directly to the construction of the new theatre, which will not only be a venue for performances, but also a centre for cultural exchange and artistic cultivation. We firmly believe that with your help, we can bring more artistic enjoyment and educational resources to the community.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1aA39P8A9aNExQ2HCtUF_ycD-71Fpmpm6Vw&usqp=CAU'),
(3, 'about', 'Our new home', 'The Genesian Theatre Company is thrilled to have found a new home only one bus stop over the Anzac bridge from our current Kent St location!\nPlans are underway for the fit out of a brand new theatre space in the parish hall of St Joseph\\s Rozelle, 2B Gordon St, to be occupied by the Genesian Theatre Company upon completion.\nFor the immediate future we will continue to perform at 420 Kent st, but will make sure to let everybody know when we have a firm date for the move to our exciting new home!\nBarry Nielsen, Genesian Theatre Director, commented "We are so pleased to have found a great new venue so close by that will be easy for our many loyal patrons to attend – and we are also keen to attract locals from the Rozelle area and offer them our tradition of high quality productions at an affordable price."\nThe new venue is just a quick 10 min hop on the bus from our current location - bus routes 500N, 502, 503, 504, 505, 506, 507 will do the job. Click map to enlarge:
Background to the move', 'https://picsum.photos/1024/480/?image=10'),
(4, 'about', 'About US','The Genesian Theatre Company celebrates our 80th year as a company in 2024! For 70 years we have been operating from our home at 420 Kent St, an historic church that has a long tradition as a theatre in the heart of the Sydney CBD. We boast among our alumni numerous household names in Australian theatre and film such as Angela Punch, Bryan Brown, Baz Luhrmann, Coral Lansbury, Judi Farr, John Bell, Peter Carroll, and Nick Enright. We\\re thrilled that some of our more recent cast members such as Tom Tilley, Rowan Witt, Lib Campbell, Aaron Glenane, and Bianca Bradey have gone on to carve out their own space in the industry in the past few years. We are also just as proud of the hundreds of other talented actors, directors, stage managers, set and costume designers, lighting and sound specialists, stage and production staff who have contributed to Sydney\\s unique little theatre. We are particularly proud of all our members who volunteer their time to work Front of House to help you enjoy your visit to the Genesian Theatre.\n Over the years the company has developed into a theatre providing a training ground for young theatre professionals and a place where those who love the theatre can meet, share, and extend their knowledge of the performing arts. The Genesian Theatre Company is one of Sydney\\s most active theatre companies. In addition to six main stage productions each year we run classes, workshops, and many other activities.\n Membership of the company is open to all people over the age of eighteen.\nThe building which houses the Genesian Theatre Company was originally St John\\s Church, and dates from 1868. It served as both a church and a poor school until 1932 when it became the Kursaal Theatre, housing the Sydney Repertory Company. In 1938 it became the first Matthew Talbot Hostel. Since 1954 it has been the home of the Genesian Theatre Company which was formed in 1944.\n The Genesian Theatre Company would like to acknowledge the Gadigal of the Eora Nation, the traditional custodians of this land our theatre stands on, and pay our respects to the Elders both past and present.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5rb3kSwAQ8kOrZoAHe8cKehACyjO5ZqssXw&usqp=CAU'),
(5, 'about', 'Our History', 'The Genesian Theatre Company came into being from a meeting of members of the Catholic Youth Organisation intent upon forming a full scale dramatic group. This meeting was held on 15th August, 1944. Named for St Genesius, Patron Saint of Actors, The Genesian Theatre Company has developed into a strong theatre company over the years.\n Monday 29th January 1945 was the Opening Night of the Genesians first production. This was The Comedian by Henri Ghéon, a drama about St. Genesius, patron saint of actors reputedly martyred by Diocletian in the year 303 A.D. and from whom the name of our theatre is derived.\n Between 1945 and 1954 the Genesians used a variety of venues for their productions. Some of the theatres used were the Australian Hall (later Rivoli Theatre), the Sydney Radio Theatre, the Conservatorium, Manresa Hall, and the Capitol Theatre. Our present theatre was originally a church dedicated to St John the Evangelist, opened on Sunday 4th October 1868.', 'https://images.squarespace-cdn.com/content/v1/591b3e9ebebafbf97085a3af/1502934854586-RLLRGACK16KLCYGMJ9QU/image-asset.png'),
(6, 'about/work', 'Become a member', 'Being a member of the Genesian Theatre Company is like being part of large family of like-minded theatre people.\n If you\\d like to experience being a member of a theatre company, we\\d welcome you to join us! You don\\t need to be experienced, we can provide training and can cater to different levels of involvement. We welcome actors, directors, stage managers, designers (Set, Costume, Lighting, Sound), the publicity people who bring in the crowds, and the admin people who keep the theatre running.\n There is a one-year probationary period for intending members during which time you have all the privileges of membership except voting rights and access to grants for theatre study. The fee for probationary membership is $33 (GST included).\n All members are required to perform Front of House duty at least once for every Genesian production - working behind the bar selling refreshments and assisting patrons to their seats. This usually works out to between 6 or 8 times per year.\n Our next meeting for prospective new members will be held soon. The meeting should last about an hour. It gives you a chance to meet some existing members and to ask questions about the theatre, and you will be able to join the Genesian Theatre at the conclusion of the meeting.', ''),
(7, 'about', 'Wheelchair access', 'The Genesian Theatre has a wheelchair space in the auditorium that is accessible via a ramp into the theatre. It\\s not on sale via our website to prevent accidental booking - so even if it looks like its booked it may well be available for your chosen performance! Please use the web form below to contact us for further information about booking the wheelchair space to attend a performance, or other accessibility concerns.
', 'https://png.pngtree.com/png-clipart/20200224/original/pngtree-wheelchair-access-icon-png-image_5247917.jpg'),
(8, 'work', 'Auditions', 'Arguably Noel Coward\\s best comedy about the mad-cap Bliss family and the games they play on their unsuspecting guests. Set in the roaring twenties, Hay Fever is a delightful comedy of an elegant weekend in the country. With Coward\\s ever-witty dialogue, Hay Fever is sure to please.', 'https://www.genesiantheatre.com.au/s2009/2/images/poster.jpg'),
(9, 'work', 'Volunteer', 'Static copy to be supplied, manual input', 'https://picsum.photos/1024/480/?image=10'),
(10, 'work', 'Apply to Direct a show', 'Static copy to be supplied, manual input', 'https://picsum.photos/1024/480/?image=10'),
(11, 'News', 'Publicity/Reviews Section', 'Static copy to be supplied, manual input with the ability to include hyperlinks', 'https://picsum.photos/1024/480/?image=10'),
(12, 'training', 'Training 1', 'edit to add content','https://picsum.photos/1024/480/?image=10'),
(13, 'training', 'Training 2', 'edit to add content','https://picsum.photos/1024/480/?image=10');



SELECT pgid,pgname,title FROM Pages; 

