-- type_uer
INSERT INTO type_user (code, name) VALUES ('STAFF', 'Staff');
INSERT INTO type_user (code, name) VALUES ('INTERNSHIP', 'Internship');
INSERT INTO type_user (code, name) VALUES ('COLLABORATOR', 'Collaborator');

-- level
-- Insert dữ liệu vào bảng level cho loại user INTERNSHIP
INSERT INTO level (code, name) 
VALUES 
('INTERN_0', 'Intern 0'),
('INTERN_1', 'Intern 1'),
('INTERN_2', 'Intern 2'),
('INTERN_3', 'Intern 3');

-- Insert dữ liệu vào bảng level cho loại user STAFF và COLLABORATOR
INSERT INTO level (code, name) 
VALUES 
('FRESHER-', 'Fresher-'),
('FRESHER', 'Fresher'),
('FRESHER+', 'Fresher+'),
('JUNIOR-', 'Junior-'),
('JUNIOR', 'Junior'),
('JUNIOR+', 'Junior+'),
('MIDDLE-', 'Middle-'),
('MIDDLE', 'Middle'),
('MIDDLE+', 'Middle+'),
('SENIOR-', 'Senior-'),
('SENIOR', 'Senior'),
('SENIOR+', 'Senior+');


-- INSERT INTO type_user_level

-- Liên kết Internship với các level intern 0, 1, 2, 3
INSERT INTO type_user_level (level_id, type_user_id) 
VALUES 
((SELECT id FROM level WHERE code = 'INTERN_0'), (SELECT id FROM type_user WHERE code = 'INTERNSHIP')),
((SELECT id FROM level WHERE code = 'INTERN_1'), (SELECT id FROM type_user WHERE code = 'INTERNSHIP')),
((SELECT id FROM level WHERE code = 'INTERN_2'), (SELECT id FROM type_user WHERE code = 'INTERNSHIP')),
((SELECT id FROM level WHERE code = 'INTERN_3'), (SELECT id FROM type_user WHERE code = 'INTERNSHIP'));

-- Liên kết Staff với các level Fresher-, Fresher, Fresher+, Junior-, Junior, Junior+, Middle-, Middle, Middle+, Senior-, Senior, Senior+
INSERT INTO type_user_level (level_id, type_user_id) 
VALUES 
((SELECT id FROM level WHERE code = 'FRESHER-'), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'FRESHER'), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'FRESHER+') , (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'JUNIOR-'), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'JUNIOR'), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'JUNIOR+') , (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'MIDDLE-' ), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'MIDDLE' ), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'MIDDLE+' ), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'SENIOR-' ), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'SENIOR'), (SELECT id FROM type_user WHERE code = 'STAFF')),
((SELECT id FROM level WHERE code = 'SENIOR+' ), (SELECT id FROM type_user WHERE code = 'STAFF'));

-- Liên kết Collaborator với các level Fresher-, Fresher, Fresher+, Junior-, Junior, Junior+, Middle-, Middle, Middle+, Senior-, Senior, Senior+
INSERT INTO type_user_level (level_id, type_user_id) 
VALUES 
((SELECT id FROM level WHERE code = 'FRESHER-' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'FRESHER' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'FRESHER+'), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'JUNIOR-' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'JUNIOR' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'JUNIOR+' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'MIDDLE-'), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'MIDDLE' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'MIDDLE+' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'SENIOR-' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'SENIOR' ), (SELECT id FROM type_user WHERE code = 'COLLABORATOR')),
((SELECT id FROM level WHERE code = 'SENIOR+'), (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
-- role
INSERT INTO role (code, name) VALUES ('STAFF', 'Staff');
INSERT INTO role (code, name) VALUES ('MANAGER', 'Manager');

-- branch
INSERT INTO branch (name, code) VALUES ('Hà Nội 1', 'HN1');
INSERT INTO branch (name, code) VALUES ('Hà Nội 2', 'HN2');
INSERT INTO branch (name, code) VALUES ('Hà Nội 3', 'HN3');
INSERT INTO branch (name, code) VALUES ('Sài Gòn 1', 'SG1');
INSERT INTO branch (name, code) VALUES ('Sài Gòn 2', 'SG2');
INSERT INTO branch (name, code) VALUES ('Đà Nẵng', 'DN');
INSERT INTO branch (name, code) VALUES ('Vinh', 'VINH');
INSERT INTO branch (name, code) VALUES ('Quy nhơn', 'QN');

-- position
INSERT INTO position (name, description) VALUES ('DEV', 'Developer');
INSERT INTO position (name, description) VALUES ('TESTER', 'Tester');
INSERT INTO position (name, description) VALUES ('IT', 'IT Specialist');
INSERT INTO position (name, description) VALUES ('PM', 'Project Manager');
INSERT INTO position (name, description) VALUES ('HR', 'Human Resources');
INSERT INTO position (name, description) VALUES ('SALE', 'Sales');
INSERT INTO position (name, description) VALUES ('BA', 'Business Analyst');
INSERT INTO position (name, description) VALUES ('INTERN-NET', 'Intern .NET Developer');
INSERT INTO position (name, description) VALUES ('INTERN-JAVA', 'Intern Java Developer');

-- -- permission
INSERT INTO permission (name, code) VALUES ('create user', 'create-user');
INSERT INTO permission (name, code) VALUES ('get user all', 'get-user-all');
INSERT INTO permission (name, code) VALUES ('get user id', 'get-user-id');
INSERT INTO permission (name, code) VALUES ('my info', 'my-info');
INSERT INTO permission (name, code) VALUES ('update user', 'update-user');
INSERT INTO permission (name, code) VALUES ('delete', 'delete-user');

INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'create-user'));
INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'get-user-all'));
INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'get-user-id'));
INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'my-info'));
INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'update-user'));
INSERT INTO role_permission (role_id,permission_id) VALUES ((SELECT id FROM role WHERE code = 'ADMIN'), (SELECT id FROM permission WHERE code = 'delete-user'));


-- Team
INSERT INTO team (name,description) VALUES ("team1" ,"team lam ve san pham 1");
INSERT INTO team (name,description) VALUES ("team2" ,"team lam ve san pham 2");
INSERT INTO team (name,description) VALUES ("team3" ,"team lam ve san pham 3");

-- project
INSERT INTO project (name , description, active , team_id) VALUES ("project1","thong tin project 1" , true , 1);
INSERT INTO project (name , description, active , team_id) VALUES ("project2","thong tin project 2" , true , 2);
INSERT INTO project (name , description, active , team_id) VALUES ("project3","thong tin project 3" , true , 3); 

-- task
INSERT INTO task (code,name,description) VALUES ("CODING", "coding" , "");
INSERT INTO task (code,name,description) VALUES ("TESTING" , "testing", "");
INSERT INTO task (code,name,description) VALUES ("PROJECT-MANAGEMENT" ,"prject management","");
INSERT INTO task (code,name,description) VALUES ("MEETING","meeting","");
INSERT INTO task (code,name,description) VALUES ("DOCUMENTING","documenting","");
INSERT INTO task (code,name,description) VALUES ("REVIEW-CODE","review code","");

-- project-task
INSERT INTO project_task(task_id,project_id) VALUES (1,1);
INSERT INTO project_task(task_id,project_id) VALUES (2,1);
INSERT INTO project_task(task_id,project_id) VALUES (3,1);
INSERT INTO project_task(task_id,project_id) VALUES (4,1);
INSERT INTO project_task(task_id,project_id) VALUES (5,1);
INSERT INTO project_task(task_id,project_id) VALUES (6,1);

INSERT INTO project_task(task_id,project_id) VALUES (1,2);
INSERT INTO project_task(task_id,project_id) VALUES (2,2);
INSERT INTO project_task(task_id,project_id) VALUES (3,2);
INSERT INTO project_task(task_id,project_id) VALUES (4,2);


INSERT INTO project_task(task_id,project_id) VALUES (1,3);
INSERT INTO project_task(task_id,project_id) VALUES (2,3);
INSERT INTO project_task(task_id,project_id) VALUES (3,3);
INSERT INTO project_task(task_id,project_id) VALUES (4,3);

-- team-user
INSERT INTO team_user(team_id,user_id) VALUES (1,1);
INSERT INTO team_user(team_id,user_id) VALUES (1,2);
INSERT INTO team_user(team_id,user_id) VALUES (1,3);

INSERT INTO team_user(team_id,user_id) VALUES (2,4);
INSERT INTO team_user(team_id,user_id) VALUES (2,5);
INSERT INTO team_user(team_id,user_id) VALUES (2,6);

-- status my request
INSERT INTO status_my_request(name,description) VALUES("Full day" , "nghi ca ngay");
INSERT INTO status_my_request(name,description) VALUES("Morning" , "nghi buoi sang");
INSERT INTO status_my_request(name,description) VALUES("Afternoon" , "nghi buoi chieu");
INSERT INTO status_my_request(name,description) VALUES("Late hour" , "di muon");
INSERT INTO status_my_request(name,description) VALUES("Early hour" , "ve som");

