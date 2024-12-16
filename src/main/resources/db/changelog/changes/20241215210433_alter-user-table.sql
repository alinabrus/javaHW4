--liquibase formatted sql
--changeset abrus:20241215210433_alter-user-table
alter table `user`
add unique key user_uk_email (email);

--rollback alter table `user` drop key user_uk_email;
