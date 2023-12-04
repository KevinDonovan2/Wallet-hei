create table if not exists count (
    id serial primary key,
    bankName varchar(50) not null,
    username varchar(50),
    countNumber varchar(255) not null,
    accoutType varchar(50) not null,
    balance float,
    idCurrency int references currency(id)
);