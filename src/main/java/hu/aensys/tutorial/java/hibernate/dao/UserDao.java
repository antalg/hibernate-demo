package hu.aensys.tutorial.java.hibernate.dao;

import hu.aensys.tutorial.java.hibernate.entity.User;

import javax.persistence.EntityManager;

public class UserDao extends JpaDao<User, Long> {

    public UserDao(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    public User findByUsername(String username) {
        String query = """
            select
                user
            from
                User user
            where
                user.username = :username
        """;

        return getEntityManager().createQuery(query, User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
