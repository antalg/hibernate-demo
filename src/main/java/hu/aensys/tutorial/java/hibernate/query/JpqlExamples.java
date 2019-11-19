package hu.aensys.tutorial.java.hibernate.query;

import hu.aensys.tutorial.java.hibernate.entity.File;
import hu.aensys.tutorial.java.hibernate.entity.User;
import hu.aensys.tutorial.java.hibernate.entity.VideoFile;
import hu.aensys.tutorial.java.hibernate.entity.VideoFileFormat;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class JpqlExamples implements QueryExamples {

    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        String query = """
            select
                user
            from
                User user
        """;

        return entityManager.createQuery(query, User.class)
            .getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
        String query = """
            select
                user
            from
                User user
            where
                user.username = :username
        """;

        return entityManager.createQuery(query, User.class)
            .setParameter("username", username)
            .getSingleResult();
    }

    @Override
    public List<File> findAllFiles() {
        String query = """
            select
                file
            from
                File file
        """;

        return entityManager.createQuery(query, File.class)
                .getResultList();
    }

    @Override
    public List<File> findAllFilesWithOwners() {
        String query = """
            select
                file
            from
                File file
            left join fetch
                file.owner owner
        """;

        return entityManager.createQuery(query, File.class)
                .getResultList();
    }

    @Override
    public List<VideoFile> findVideoFilesByFormats(VideoFileFormat... formats) {
        String query = """
            select
                videoFile
            from
                VideoFile videoFile
            left join fetch
                videoFile.owner owner
            where
                videoFile.format in (:formats)
        """;

        return entityManager.createQuery(query, VideoFile.class)
                .setParameter("formats", Arrays.asList(formats))
                .getResultList();
    }

    @Override
    public List<VideoFileCount> countVideoFilesGroupedByFormat() {
        String query = """
            select
                new hu.aensys.tutorial.java.hibernate.query.VideoFileCount(
                    videoFile.format,
                    count(videoFile.id)
                )
            from
                VideoFile videoFile
            group by
                videoFile.format
            order by
                videoFile.format
        """;

        return entityManager.createQuery(query, VideoFileCount.class)
                .getResultList();
    }

}
