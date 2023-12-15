DROP TABLE IF EXISTS account;
CREATE TYPE balance as(
    amount double,
    lastDateUpdate timestamp without time zone
);
CREATE TABLE IF NOT EXISTS account(
    accountId varchar(255) primary key default gen_random_uuid(),
    accountName varchar(50) not null,
    balance balance not null,
    transactions text,
    idCurrencyCount varchar(255) reference currency(currencyId),
    accountType varchar(20) CHECK (accountType IN('Bank', 'Cash', 'Mobile money')) not null
);