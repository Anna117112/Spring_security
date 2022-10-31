create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);
create table autorites (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);
CREATE TABLE roles_autorites (
  role_id               int not null,
  autorites_id             int not null,
  primary key (role_id, autorites_id),
  foreign key (role_id) references roles (id),
  foreign key (autorites_id) references autorites (id)
);

CREATE TABLE users_autorites (
  user_id               bigint not null,
  autorites_id               int not null,
  primary key (user_id, autorites_id),
  foreign key (user_id) references users (id),
  foreign key (autorites_id) references autorites (id)
);
insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into autorites (name)
values
('READ'), ('UPDATE');

insert into users (username, password, email)
values
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 2),
(2, 1);

insert into roles_autorites (role_id, autorites_id)
values
(1, 1),
(2, 2),
(2, 1);

insert into users_autorites(user_id, autorites_id)
values
(1, 2),
(2, 1);
