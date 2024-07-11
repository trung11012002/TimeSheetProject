-- type_uer
INSERT INTO type_user (code, name) VALUES ('STAFF', 'Staff');
INSERT INTO type_user (code, name) VALUES ('INTERNSHIP', 'Internship');
INSERT INTO type_user (code, name) VALUES ('COLLABORATOR', 'Collaborator');

-- level

--level staff
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER-', 'Fresher-', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER', 'Fresher', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER+', 'Fresher+', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR-', 'Junior-', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR', 'Junior', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR+', 'Junior+', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE-', 'Middle-', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE', 'Middle', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE+', 'Middle+', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR-', 'Senior-', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR', 'Senior', (SELECT id FROM type_user WHERE code = 'STAFF'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR+', 'Senior+', (SELECT id FROM type_user WHERE code = 'STAFF'));

--level Internship
INSERT INTO level (code, name, type_user_id) VALUES ('INTERN_0', 'Intern 0', (SELECT id FROM type_user WHERE code = 'INTERNSHIP'));
INSERT INTO level (code, name, type_user_id) VALUES ('INTERN_1', 'Intern 1', (SELECT id FROM type_user WHERE code = 'INTERNSHIP'));
INSERT INTO level (code, name, type_user_id) VALUES ('INTERN_2', 'Intern 2', (SELECT id FROM type_user WHERE code = 'INTERNSHIP'));
INSERT INTO level (code, name, type_user_id) VALUES ('INTERN_3', 'Intern 3', (SELECT id FROM type_user WHERE code = 'INTERNSHIP'));

--levl Collaborator
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER-', 'Fresher-', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER', 'Fresher', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('FRESHER+', 'Fresher+', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR-', 'Junior-', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR', 'Junior', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('JUNIOR+', 'Junior+', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE-', 'Middle-', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE', 'Middle', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('MIDDLE+', 'Middle+', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR-', 'Senior-', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR', 'Senior', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));
INSERT INTO level (code, name, type_user_id) VALUES ('SENIOR+', 'Senior+', (SELECT id FROM type_user WHERE code = 'COLLABORATOR'));

-- role
INSERT INTO role (code, name) VALUES ('STAFF', 'Staff');
INSERT INTO role (code, name) VALUES ('ADMIN', 'Admin');
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

--position
INSERT INTO position (name, description) VALUES ('DEV', 'Developer');
INSERT INTO position (name, description) VALUES ('TESTER', 'Tester');
INSERT INTO position (name, description) VALUES ('IT', 'IT Specialist');
INSERT INTO position (name, description) VALUES ('PM', 'Project Manager');
INSERT INTO position (name, description) VALUES ('HR', 'Human Resources');
INSERT INTO position (name, description) VALUES ('SALE', 'Sales');
INSERT INTO position (name, description) VALUES ('BA', 'Business Analyst');
INSERT INTO position (name, description) VALUES ('INTERN-NET', 'Intern .NET Developer');
INSERT INTO position (name, description) VALUES ('INTERN-JAVA', 'Intern Java Developer');

-- permission
INSERT INTO permission (name, code,role_id) VALUES ('create user', 'create-user',(SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO permission (name, code,role_id) VALUES ('get all user', 'get-all-user',(SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO permission (name, code,role_id) VALUES ('get user id', 'get-user-id',(SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO permission (name, code,role_id) VALUES ('my info', 'my-info',(SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO permission (name, code,role_id) VALUES ('update user', 'update-user',(SELECT id FROM role WHERE code = 'ADMIN'));
INSERT INTO permission (name, code,role_id) VALUES ('delete', 'delete-user',(SELECT id FROM role WHERE code = 'ADMIN'));


