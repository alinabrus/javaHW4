package com.abrus.user.repository;

import com.abrus.user.dto.UserDto;
import com.abrus.user.model.User;
import com.abrus.user.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public User create(User user) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    public User update(User user) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager
                    .createQuery("select u from User u", User.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(User.class, id));
        }
    }

    @Override
    public List<UserDto> findAllWithRoles() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            Query q = entityManager.createNativeQuery("""
                select u.id, u.name, u.email, u.phone, group_concat(r.name) as roles
                from user u
                left join user_role ur on (u.id = ur.user_id)
                left join role r on (ur.role_id = r.id)
                group by u.id
                """, UserDto.class);
            return (List<UserDto>) q.getResultList();
        }
    }
}
