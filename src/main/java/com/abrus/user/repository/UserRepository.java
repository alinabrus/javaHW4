package com.abrus.user.repository;

import com.abrus.user.dto.UserDto;
import com.abrus.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);

    User update(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    List<UserDto> findAllWithRoles();
}
