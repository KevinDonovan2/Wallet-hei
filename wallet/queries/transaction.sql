create table if not exists transaction(
    id serial primary key,
    amount float,
    type_transaction varchar(255),
    date_transaction date,
    id_count int references count(id)
);