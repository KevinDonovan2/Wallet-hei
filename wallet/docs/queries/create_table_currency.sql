DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS currency(
    currencyId varchar(255) primary key default gen_random_uuid(),
    currencyName varchar(20) not null,
    currencyCode varchar(10) CHECK (currencyCode IN ('EUR', 'MGA')) not null
);