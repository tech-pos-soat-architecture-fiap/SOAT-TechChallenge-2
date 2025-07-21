package br.com.fiap.TechFood.infrastructure.adapter.in.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquenessValidator implements ConstraintValidator<Unique, Object> {

    private Class<?> entity;
    private String field;

    private final EntityManager entityManager;

    UniquenessValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Unique unique) {
        entity = unique.entity();
        field = unique.field();
    }

    @Override
    public boolean isValid(Object valueToBeValidated, ConstraintValidatorContext context) {
        String jpql = String.format("select count(1) > 0 from %s where %s = :value", entity.getSimpleName(), field);

        boolean existsInDB = entityManager
                .createQuery(jpql, Boolean.class)
                .setParameter("value", valueToBeValidated)
                .getSingleResult();

        return !existsInDB;
    }
}
