package hu.aensys.tutorial.java.hibernate;

import hu.aensys.tutorial.java.hibernate.common.PersistenceUtil;
import hu.aensys.tutorial.java.hibernate.entity.*;
import hu.aensys.tutorial.java.hibernate.factory.EntityFactory;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
public class DatabaseInitializer {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = PersistenceUtil.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user1 = EntityFactory.createUser("user1", "Test User 1");
        User user2 = EntityFactory.createUser("user2", "Test User 2");

        Stream.of(user1, user2)
                .forEach(entityManager::persist);

        File file = EntityFactory.createFile("test_document.pdf", new Date(), user1);
        ImageFile imageFile = EntityFactory.createImageFile("test_photo.jpg", new Date(), user2, 1920, 1080);
        VideoFile videoFile1 = EntityFactory.createVideoFile("test_video.mp4", new Date(), user1, TimeUnit.SECONDS.toMillis(2), VideoFileFormat.MP4);
        VideoFile videoFile2 = EntityFactory.createVideoFile("test_video.mkv", new Date(), user2, TimeUnit.MINUTES.toMillis(10), VideoFileFormat.MKV);
        VideoFile videoFile3 = EntityFactory.createVideoFile("test_video.avi", new Date(), user1, TimeUnit.SECONDS.toMillis(20), VideoFileFormat.AVI);
        VideoFile videoFile4 = EntityFactory.createVideoFile("test_video2.avi", new Date(), user2, TimeUnit.MINUTES.toMillis(30), VideoFileFormat.AVI);

        Stream.of(file, imageFile, videoFile1, videoFile2, videoFile3, videoFile4)
                .forEach(entityManager::persist);

        transaction.commit();

        log.info("Database successfully initialized.");

        entityManager.close();
        entityManagerFactory.close();
    }

}
