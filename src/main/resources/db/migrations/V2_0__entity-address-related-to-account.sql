CREATE TABLE IF NOT EXISTS address
(
    id           VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    number       VARCHAR(255) NOT NULL,
    postal_code  VARCHAR(255) NOT NULL,
    state        VARCHAR(255) NOT NULL,
    street       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS account
    ADD COLUMN address_id VARCHAR(255);

ALTER TABLE IF EXISTS account
    ADD CONSTRAINT fk_account_on_address FOREIGN KEY (address_id) REFERENCES address (id);