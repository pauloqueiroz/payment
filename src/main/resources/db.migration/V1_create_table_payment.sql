create table payments (
   id bigserial not null,
    code varchar(3),
    expiration varchar(7),
    name varchar(100),
    number varchar(19),
    order_id bigint not null,
    payment_method_id bigint not null,
    status varchar(255) not null,
    value numeric(38,2) not null,
    primary key (id)
);