--liquibase formatted sql
--changeset abrus:20241215201208_create-user-table
create table `user` (
    id bigint not null auto_increment,
    email varchar(255),
    name varchar(255),
    phone varchar(255),
    primary key (id)
) engine=InnoDB;

--rollback DROP TABLE `user`;
