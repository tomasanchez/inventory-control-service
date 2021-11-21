-- Creates the new database
DROP DATABASE IF EXISTS inventory;
CREATE DATABASE IF NOT EXISTS inventory;
CREATE USER  IF NOT EXISTS 'springuser'@'%' identified by 'admin'; -- Creates the user
GRANT ALL ON inventory.* to 'springuser'@'%';
