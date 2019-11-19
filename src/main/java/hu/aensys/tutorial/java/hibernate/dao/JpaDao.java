package hu.aensys.tutorial.java.hibernate.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@AllArgsConstructor
public class JpaDao<EntityType, IdentifierType> implements Dao<EntityType, IdentifierType> {

    @Getter(AccessLevel.PROTECTED)
    private final Class<EntityType> entityClass;

    @Getter(AccessLevel.PROTECTED)
    private final EntityManager entityManager;

    public EntityType findByIdentifier(final IdentifierType identifier) {
        return entityManager.find(entityClass, identifier);
    }

    public List<EntityType> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<EntityType> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<EntityType> entityRoot = criteriaQuery.from(entityClass);
        criteriaQuery.select(entityRoot);

        TypedQuery<EntityType> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void persist(final EntityType entity) {
        entityManager.persist(entity);
    }

    public void remove(final EntityType entity) {
        entityManager.remove(entity);
    }

    public void refresh(final EntityType entity) {
        entityManager.refresh(entity);
    }

    public EntityType merge(final EntityType entity) {
        return entityManager.merge(entity);
    }

    public void flush() {
        entityManager.flush();
    }

}
