INSERT INTO title (name,code,description,deleted) VALUES ('Üretim Takım Üyesi','UTU','Üretim Takım Üyesi',0);
INSERT INTO title (name,code,description,deleted) VALUES ('Üretim Takım Lideri','UTL','Üretim Takım Lideri',0);
INSERT INTO title (name,code,description,deleted) VALUES ('Grup Yöneticisi','GY','Grup Yöneticisi',0); 

    
INSERT INTO employee (name,surname,work_start_date,manager_id,title_id,employee_number,deleted) VALUES ('Pakize','Karabekmez','2010-01-01 00:00:00',NULL,(Select id from title where code = 'GY'),85632,0);    
INSERT INTO employee (name,surname,work_start_date,manager_id,title_id,employee_number,deleted) VALUES ('Aykut','Yeşilbağ','2007-09-01 00:00:00',(SELECT t.id FROM (SELECT * FROM employee) t WHERE t.title_id = (Select id from title where code = 'GY')),(Select id from title where code = 'UTL'),91506,0);
INSERT INTO employee (name,surname,work_start_date,manager_id,title_id,employee_number,deleted) VALUES ('Salman Burak','Yeşilbağ','2020-03-21 00:00:00',(SELECT t.id FROM (SELECT * FROM employee) t WHERE t.title_id = (Select id from title where code = 'UTL')),(Select id from title where code = 'UTU'),97891,0);

INSERT INTO employee (name,surname,work_start_date,manager_id,title_id,employee_number,deleted) VALUES ('Ahmet','Yeşilbağ','2021-01-09 00:00:00',(SELECT t.id FROM (SELECT * FROM employee) t WHERE t.title_id = (Select id from title where code = 'UTL')),(Select id from title where code = 'UTU'),200000,0);


INSERT INTO day_off (annual_permit,start_date,finish_date,employee_id,deleted) VALUES (15,'2021-03-21 00:00:00','2022-03-21 00:00:00',(Select id from employee where employee_number = 97891),0); 

INSERT INTO day_off (annual_permit,start_date,finish_date,employee_id,deleted) VALUES (0,'2021-01-09 00:00:00','2022-01-09 00:00:00',(Select id from employee where employee_number = 200000),0); 