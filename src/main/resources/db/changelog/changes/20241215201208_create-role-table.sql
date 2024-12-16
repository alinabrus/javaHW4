--liquibase formatted sql
--changeset abrus:20241215201208_create-role-table
create table `role` (
    id bigint not null auto_increment,
    name enum ('GROUP_ADMIN','SYSTEM_ADMIN','USER') not null,
    primary key (id),
    unique key user_role_uk_name (name)
) engine=InnoDB;

--rollback DROP TABLE `role`;
