create table if not exists count (
    id serial primary key,
    username varchar(50),
    accoutType varchar(50),
    balance float,
    idCurrency int references currency(id)
);