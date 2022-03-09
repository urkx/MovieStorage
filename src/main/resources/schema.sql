create table Movie (
    id identity,
    title varchar(100) not null,
    rating real not null,
    yearPremiered integer
);

create table Genre (
    id identity,
    name varchar(100)
);
