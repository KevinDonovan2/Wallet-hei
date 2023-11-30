create table if not exists count (
    id serial primary key,
    username varchar(50),
    accout_type varchar(50),
    balance float,
    id_currency int references currency(id)
);