package me.fit.model;

import jakarta.persistence.*;

import java.io.File;
import java.util.Objects;

@Entity
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uploaded_file_seq")
    @SequenceGenerator(name = "uploaded_file_seq", sequenceName = "uploaded_file_seq", allocationSize = 1)
    private Long id;

    private String filename;

    @Transient
    private File file;

    public UploadedFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UploadedFile that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", file=" + file +
                '}';
    }
}
