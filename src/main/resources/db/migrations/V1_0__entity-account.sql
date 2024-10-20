CREATE TABLE IF NOT EXISTS account
(
    id             VARCHAR(255)   NOT NULL,
    account_number VARCHAR(255)   NOT NULL,
    balance        NUMERIC(38, 2) NOT NULL,
    name           VARCHAR(255)   NOT NULL,
    phone          VARCHAR(255),
    status         VARCHAR(255)   NOT NULL,
    tax_identifier VARCHAR(255)   NOT NULL,
    version        BIGINT         NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id),
    CONSTRAINT uc_account_account_number UNIQUE (account_number)
);
