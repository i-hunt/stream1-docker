CREATE USER ihunt with encrypted password 'password';
CREATE DATABASE ihunt ENCODING 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';
GRANT ALL PRIVILEGES ON DATABASE ihunt TO ihunt;

