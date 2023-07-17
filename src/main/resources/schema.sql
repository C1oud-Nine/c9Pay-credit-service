DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          serialNumber VARCHAR(255) NOT NULL UNIQUE,
                          creditAmount BIGINT NOT NULL UNIQUE
);
