CREATE TABLE USERS(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),password VARCHAR(255), date_of_birth DATE,
        city VARCHAR(255),state VARCHAR(255), country VARCHAR(255),
        email varchar(60), createdBy BIGINT NOT NULL , createdAt DATETIME NOT NULL,
        updatedBy BIGINT NOT NULL , updatedAt DATETIME NOT NULL);
