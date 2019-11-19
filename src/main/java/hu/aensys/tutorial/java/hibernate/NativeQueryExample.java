package hu.aensys.tutorial.java.hibernate;

import hu.aensys.tutorial.java.hibernate.common.PersistenceUtil;
import hu.aensys.tutorial.java.hibernate.entity.VideoFile;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
public class NativeQueryExample {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = PersistenceUtil.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = """
            select
                *
            from
                video_file
            inner join
                file on file.id = video_file.id
            inner join
                application_user on file.owner_id = application_user.id
            where
                application_user.username = :username
            order by
                file.name
        """;

        List<VideoFile> videoFiles = entityManager.createNativeQuery(query, VideoFile.class)
                .setParameter("username", "user1")
                .getResultList();

        log.info("Video files of 'user1': {}", videoFiles);

        entityManager.close();
        entityManagerFactory.close();
    }

}
