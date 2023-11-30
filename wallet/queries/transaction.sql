create table if not exists transaction(
    id serial primary key,
    amount float,
    typeTransaction varchar(255),
    datetransaction date,
    idCount int references count(id)
);