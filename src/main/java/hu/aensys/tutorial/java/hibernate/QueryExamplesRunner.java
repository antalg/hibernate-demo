package hu.aensys.tutorial.java.hibernate;

import hu.aensys.tutorial.java.hibernate.common.PersistenceUtil;
import hu.aensys.tutorial.java.hibernate.entity.File;
import hu.aensys.tutorial.java.hibernate.entity.User;
import hu.aensys.tutorial.java.hibernate.entity.VideoFile;
import hu.aensys.tutorial.java.hibernate.entity.VideoFileFormat;
import hu.aensys.tutorial.java.hibernate.query.CriteriaExamples;
import hu.aensys.tutorial.java.hibernate.query.JpqlExamples;
import hu.aensys.tutorial.java.hibernate.query.QueryExamples;
import hu.aensys.tutorial.java.hibernate.query.VideoFileCount;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class QueryExamplesRunner {

    public static void main(String[] args) {
        runExample(queryExamples -> {
            List<User> users = queryExamples.findAllUsers();
            log.info("All users: {}", users);
        });

        runExample(queryExamples -> {
            User user1 = queryExamples.findUserByUsername("user1");
            log.info("User with username 'user1': {}", user1);
        });

        runExample(queryExamples -> {
            log.info("All files:");
            List<File> files = queryExamples.findAllFiles();
            files.forEach(
                    file -> log.info("File name: {}, Owner name: {}", file.getName(), file.getOwner().getFullName())
            );
        });

        runExample(queryExamples -> {
            log.info("All files with owners fetched:");
            List<File> filesWithOwners = queryExamples.findAllFilesWithOwners();
            filesWithOwners.forEach(
                    file -> log.info("File name: {}, Owner name: {}", file.getName(), file.getOwner().getFullName())
            );
        });

        runExample(queryExamples -> {
            List<VideoFile> videoFiles = queryExamples.findVideoFilesByFormats(VideoFileFormat.MP4, VideoFileFormat.MKV);
            log.info("Video files with MP4 or MKV format: {}", videoFiles);
        });

        runExample(queryExamples -> {
            List<VideoFileCount> videoFileCounts = queryExamples.countVideoFilesGroupedByFormat();
            log.info("Number of video files grouped by format: {}", videoFileCounts);
        });
    }

    private static void runExample(Consumer<QueryExamples> exampleRunner) {
        EntityManagerFactory entityManagerFactory = PersistenceUtil.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        exampleRunner.accept(createExamples(entityManager));

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("-----------------------------------------------------------------------------------------");
    }

    // Note: here you can choose running examples with JPQL or Criteria
    private static QueryExamples createExamples(EntityManager entityManager) {
        return new JpqlExamples(entityManager);
        //return new CriteriaExamples(entityManager);
    }

}
