DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
  account_number BIGINT NOT NULL,
  balance DECIMAL(13, 2) DEFAULT 0.00,
  PRIMARY KEY (account_number),
  UNIQUE INDEX (account_number)
);

