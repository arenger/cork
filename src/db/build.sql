create table person (
   id   integer     not null generated always as identity,
   name varchar(64) not null,
   CONSTRAINT ppk PRIMARY KEY (id)
);

create table message (
   id        integer  not null generated always as identity,
   from_pid  integer  not null,
   to_pid    integer,
   content    clob,
   constraint mpk  primary key (id),
   constraint mfk2 foreign key (from_pid) references person (id),
   constraint mfk1 foreign key (to_pid)   references person (id)
);
