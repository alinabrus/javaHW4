--liquibase formatted sql
--changeset abrus:20241215201208_create-user_role-table
create table user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint user_role_fk_user_id
        foreign key  (user_id)
        references user (id)
        on delete no action
        on update no action,
    constraint user_role_fk_role_id
        foreign key (role_id)
            references role (id)
            on delete no action
            on update no action
) engine=InnoDB;

--rollback DROP TABLE `user_role`;
