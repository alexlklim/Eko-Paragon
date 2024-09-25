# mysql -u root -p



CREATE DATABASE IF NOT EXISTS eko_paragon;

CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY '1Qazimodo237!';

-- Grant full privileges to root for the hydra_b2b database
GRANT ALL PRIVILEGES ON eko_paragon.* TO 'root'@'localhost';

-- Apply the changes
FLUSH PRIVILEGES;

USE eko_paragon;

SHOW DATABASES;