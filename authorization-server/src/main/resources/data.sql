INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('web', '{bcrypt}$2a$10$Dr0V5ijSWw8Gu3upSLUh4.Vh8..cAnOqptvyJjsCKQQbS943twLt6', 'http://localhost:8080/expenses', 'READ,WRITE', '3600', '10000', 'expense', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO PERMISSION (PERMISSION_NAME) VALUES
 ('create_expense'),
 ('read_expense'),
 ('update_expense');

INSERT INTO role (NAME) VALUES
		('ROLE_admin'),('ROLE_bookkeper');

INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
     (1,1),
     (2,1),
     (3,1),
     (1,2),
     (2,2);
insert into user (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES ('1', 'santosh','{bcrypt}$2a$10$61HG6ks6aHAb2ZsIxPRpjOVuplwdR2ZkW7UXNXs1PRxhgzAf6H8f6', 'santosh@gmail.com', '0', '1', '1', '1');
insert into  user (id, username,password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) VALUES ('2', 'isha', '{bcrypt}$2a$10$1M16V/0ftJbpEiJGXBnKf.2QOqE4NK5VfbOVGwTdMRSpLt4d37Cha','isha@gmail.com', '1', '1', '1', '1');

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
VALUES
(1, 1),
(2, 2);