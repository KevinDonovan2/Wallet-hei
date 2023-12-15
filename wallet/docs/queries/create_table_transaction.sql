DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS transactions(
    transactionId varchar(255) primary key default gen_random_uuid(),
    label varchar(255) not null,
    amount double not null,
    transactionDateTime timestamp
);
--Response TD2
ALTER TABLE transactions
ADD COLUMN IF NOT EXISTS categoryId varchar(255) not null REFERENCES categories(idCategory);
