package hu.aensys.tutorial.java.hibernate.query;

import hu.aensys.tutorial.java.hibernate.entity.File;
import hu.aensys.tutorial.java.hibernate.entity.User;
import hu.aensys.tutorial.java.hibernate.entity.VideoFile;
import hu.aensys.tutorial.java.hibernate.entity.VideoFileFormat;

import java.util.List;

public interface QueryExamples {

    List<User> findAllUsers();

    User findUserByUsername(String username);

    List<File> findAllFiles();

    List<File> findAllFilesWithOwners();

    List<VideoFile> findVideoFilesByFormats(VideoFileFormat... formats);

    List<VideoFileCount> countVideoFilesGroupedByFormat();

}
