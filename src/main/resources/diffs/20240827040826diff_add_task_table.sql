CREATE TABLE IF NOT EXISTS tasks (
  id varchar(36) NOT NULL,
  title varchar(255) NOT NULL,
  description longtext NULL,
  descriptionHtml longtext NULL,
  priority int NULL,
  status int NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  userId varchar(36) NOT NULL,
  PRIMARY KEY (id)
);