package com.abrus.user.repository;

import com.abrus.user.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Role create(Role role);

    Role update(Role role);

    Optional<Role> findById(long id);

    List<Role> findAll();
}
