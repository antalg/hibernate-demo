package hu.aensys.tutorial.java.hibernate.query;

import hu.aensys.tutorial.java.hibernate.entity.File;
import hu.aensys.tutorial.java.hibernate.entity.User;
import hu.aensys.tutorial.java.hibernate.entity.VideoFile;
import hu.aensys.tutorial.java.hibernate.entity.VideoFileFormat;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@AllArgsConstructor
public class CriteriaExamples implements QueryExamples {

    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public User findUserByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery
                .select(userRoot)
                .where(criteriaBuilder.equal(userRoot.get("username"), username));

        return entityManager.createQuery(criteriaQuery)
                .getSingleResult();
    }

    @Override
    public List<File> findAllFiles() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);

        Root<File> fileRoot = criteriaQuery.from(File.class);
        criteriaQuery.select(fileRoot);

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<File> findAllFilesWithOwners() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);

        Root<File> fileRoot = criteriaQuery.from(File.class);
        fileRoot.fetch("owner", JoinType.LEFT);
        criteriaQuery.select(fileRoot);

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<VideoFile> findVideoFilesByFormats(VideoFileFormat... formats) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VideoFile> criteriaQuery = criteriaBuilder.createQuery(VideoFile.class);

        Root<VideoFile> videoFileRoot = criteriaQuery.from(VideoFile.class);
        videoFileRoot.fetch("owner", JoinType.LEFT);
        criteriaQuery
                .select(videoFileRoot)
                .where(videoFileRoot.get("format").in(formats));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<VideoFileCount> countVideoFilesGroupedByFormat() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VideoFileCount> criteriaQuery = criteriaBuilder.createQuery(VideoFileCount.class);

        Root<VideoFile> videoFileRoot = criteriaQuery.from(VideoFile.class);
        criteriaQuery
                .select(
                        criteriaBuilder.construct(
                                VideoFileCount.class,
                                videoFileRoot.get("format"),
                                criteriaBuilder.count(videoFileRoot.get("id"))
                        )
                )
                .groupBy(videoFileRoot.get("format"))
                .orderBy(criteriaBuilder.asc(videoFileRoot.get("format")));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

}
