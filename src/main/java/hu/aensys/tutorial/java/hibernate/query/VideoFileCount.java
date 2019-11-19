package hu.aensys.tutorial.java.hibernate.query;

import hu.aensys.tutorial.java.hibernate.entity.VideoFileFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoFileCount {

    private VideoFileFormat format;

    private Long numberOfFiles;

}
