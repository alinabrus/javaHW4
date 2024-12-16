package com.abrus.user.repository;

import com.abrus.user.model.Role;
import com.abrus.user.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    private final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public Role create(Role role) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return role;
    }

    public Role update(Role role) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return role;
    }

    @Override
    public Optional<Role> findById(long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
           return Optional.ofNullable(entityManager.find(Role.class, id));
        }
    }

    @Override
    public List<Role> findAll() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager
                    .createQuery("select r from Role r join fetch r.assignees", Role.class)
                    .getResultList();
        }
    }
}
