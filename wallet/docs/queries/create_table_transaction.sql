DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS transactions(
    transactionId serial primary key,
    label varchar(255) not null,
    amount double not null,
    transactionDateTime timestamp,
    transactionType varchar(20) CHECK (transactionType IN ('debit', 'credit', 'transfert')) not null
);