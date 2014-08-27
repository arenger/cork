create table person (
   id    integer      not null,
   name  varchar2(64) not null,
   CONSTRAINT ppk PRIMARY KEY (id)
);

create table message (
   id       integer  not null,
   from_id  integer  not null,
   to_id    integer,
   content  clob,
   constraint mpk  primary key (id),
   constraint mfk2 foreign key (from_id) references person (id),
   constraint mfk1 foreign key (to_id)   references person (id)
);
