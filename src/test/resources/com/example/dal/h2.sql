create table person (
   id    int primary key,
   name  varchar not null,
);

create table message (
   id        int  primary key,
   from_pid  int  not null,
   to_pid    int,
   content  clob,
   foreign key (from_pid) references person(id),
   foreign key (to_pid)   references person(id)
);
