--response TD2
DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS categories (
    idCategory varchar(255) primary key default gen_random_uuid(),
    categoryName varchar(100) unique not null,
    transactionType varchar(100) not null CHECK (transactionType IN ('Credit', 'debit', 'pret'))
);
