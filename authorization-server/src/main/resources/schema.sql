SET MODE MYSQL;

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id varchar(255) not null PRIMARY KEY,
  client_secret varchar(255) not null,
  web_server_redirect_uri varchar(2048) default null,
  scope varchar(255) default null,
  access_token_validity int(11) default null,
  refresh_token_validity int(11) default null,
  resource_ids varchar(1024) default null,
  authorized_grant_types varchar(1024) default null,
  authorities varchar(1024) default null,
  additional_information varchar(4096) default null,
  autoapprove varchar(255) default null
);


CREATE TABLE IF NOT EXISTS permission (
  id int(11) not null auto_increment,
  permission_name varchar(512) default null,
  primary key (id),
  unique key permission_name (permission_name)
);


CREATE TABLE IF NOT EXISTS role (
  id int(11) not null auto_increment,
  name varchar(255) default null,
  primary key (id),
  unique key name (name)
);


CREATE TABLE IF NOT EXISTS user (
  id int(11) not null auto_increment,
  username varchar(100) not null,
  password varchar(1024) not null,
  email varchar(1024) not null,
  enabled tinyint(4) not null,
  account_non_expired tinyint(4) not null,
  credentials_non_expired tinyint(4) not null,
  account_non_locked tinyint(4) not null,
  primary key (id),
  unique key username (username)
);


CREATE TABLE IF NOT EXISTS permission_role (
  permission_id int(11) default null,
  role_id int(11) default null,
  constraint permission_role_ibfk_1 foreign key (permission_id) references permission (id),
  constraint permission_role_ibfk_2 foreign key (role_id) references role (id)
);


CREATE TABLE IF NOT EXISTS role_user (
  role_id int(11) default null,
  user_id int(11) default null,
  constraint role_user_ibfk_1 foreign key (role_id) references role (id),
  constraint role_user_ibfk_2 foreign key (user_id) references user (id)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token varchar(2048),
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token varchar(2048),
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication varchar(2048),
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token varchar(2048),
  authentication varchar(2048)
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication varchar(2048)
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
