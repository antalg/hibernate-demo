package hu.aensys.tutorial.java.hibernate.dao;

import java.util.List;

public interface Dao<EntityType, IdentifierType> {

    EntityType findByIdentifier(final IdentifierType identifier);

    List<EntityType> findAll();

    void persist(final EntityType entity);

    void remove(final EntityType entity);

    void refresh(final EntityType entity);

    EntityType merge(final EntityType entity);

    void flush();

}
