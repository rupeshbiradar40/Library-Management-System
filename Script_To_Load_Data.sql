LOAD Data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/books.csv'
into table Resources
fields terminated by '	'
Enclosed by ""
Lines terminated by '\n';

SELECT * FROM RESOURCES;

LOAD Data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/user.csv'
into table User
fields terminated by ','
Enclosed by ""
Lines terminated by '\n';

select * from User;
