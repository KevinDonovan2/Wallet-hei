DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS currency(
    currencyId serial primary key,
    currencyName varchar(20) not null,
    currencyCode varchar(10) CHECK (currencyCode IN ('EUR', 'MGA')) not null
);