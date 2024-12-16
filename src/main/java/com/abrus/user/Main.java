package com.abrus.user;

import com.abrus.user.model.Role;
import com.abrus.user.model.RoleName;
import com.abrus.user.model.User;
import com.abrus.user.repository.RoleRepository;
import com.abrus.user.repository.RoleRepositoryImpl;
import com.abrus.user.repository.UserRepository;
import com.abrus.user.repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        RoleRepository roleRepository = new RoleRepositoryImpl();

        User user1 = new User();
        user1.setName("John Black");
        user1.setEmail("john@black.com");
        userRepository.create(user1);

        User user2 = new User();
        user2.setName("Mary White");
        user2.setEmail("mary@white.com");
        userRepository.create(user2);

        Role role0 = new Role();
        role0.setName(RoleName.USER);
        roleRepository.create(role0);

        Role role1 = new Role();
        role1.setName(RoleName.GROUP_ADMIN);
        roleRepository.create(role1);
        role1.setAssignees(List.of(user1, user2));
        roleRepository.update(role1);

        Role role2 = new Role();
        role2.setName(RoleName.SYSTEM_ADMIN);
        roleRepository.create(role2);
        role2.setAssignees(List.of(user1));
        roleRepository.update(role2);

        user2.setPhone("+1(917)123-4567");
        userRepository.update(user2);

        System.out.println("\nAll users: \n");
        userRepository.findAll().forEach(System.out::println);
        System.out.println("\nAll roles: \n");
        roleRepository.findAll().forEach(System.out::println);

        System.out.println("\nAll users with roles: \n");
        userRepository.findAllWithRoles().forEach(System.out::println);

        System.out.println("\nUser with id=1: " + userRepository.findById(1L));
        System.out.println("\nRole with id=1: " + roleRepository.findById(1L));
    }
}
