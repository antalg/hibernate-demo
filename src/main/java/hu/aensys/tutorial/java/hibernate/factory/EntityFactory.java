package hu.aensys.tutorial.java.hibernate.factory;

import hu.aensys.tutorial.java.hibernate.entity.*;

import java.util.Date;

public class EntityFactory {

    public static User createUser(String username, String fullName) {
        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        return user;
    }

    public static File createFile(String name, Date creationDate, User owner) {
        File file = new File();
        file.setName(name);
        file.setCreationDate(creationDate);
        file.setOwner(owner);
        return file;
    }

    public static ImageFile createImageFile(String name, Date creationDate, User owner, int width, int height) {
        ImageFile imageFile = new ImageFile();
        imageFile.setName(name);
        imageFile.setCreationDate(creationDate);
        imageFile.setOwner(owner);
        imageFile.setSize(createImageSize(width, height));
        return imageFile;
    }

    public static ImageSize createImageSize(int width, int height) {
        ImageSize imageSize = new ImageSize();
        imageSize.setWidth(width);
        imageSize.setHeight(height);
        return imageSize;
    }

    public static VideoFile createVideoFile(String name, Date creationDate, User owner, Long lengthInMilliseconds, VideoFileFormat format) {
        VideoFile videoFile = new VideoFile();
        videoFile.setName(name);
        videoFile.setCreationDate(creationDate);
        videoFile.setOwner(owner);
        videoFile.setLengthInMilliseconds(lengthInMilliseconds);
        videoFile.setFormat(format);
        return videoFile;
    }

}
