package hu.aensys.tutorial.java.hibernate;

import hu.aensys.tutorial.java.hibernate.common.PersistenceUtil;
import hu.aensys.tutorial.java.hibernate.entity.User;
import hu.aensys.tutorial.java.hibernate.query.JpqlExamples;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class EntityUpdateExample {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = PersistenceUtil.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = new JpqlExamples(entityManager).findUserByUsername("user1");
        user.setFullName("Test User 11");

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

}
