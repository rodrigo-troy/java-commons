package com.rodrigotroy.commons.core.file;

import com.rodrigotroy.commons.core.util.ByteUtil;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class BinaryFile {
    private final byte @NotNull [] fileContent;
    private final String fileName;
    private String fileExtension;

    public static BinaryFile fromCurrentDirectory(@NotNull String resourceName) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Path.of(resourceName))) {
            byte[] content = ByteUtil.toByteArray(inputStream);

            return new BinaryFile(content,
                                  resourceName);
        }
    }

    public static BinaryFile fromResourceAsStream(String resourceName) throws IOException {
        if (Validator.isEmpty(resourceName)) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }

        try (InputStream inputStream = PropertiesFile.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }

            byte[] content = ByteUtil.toByteArray(inputStream);

            return new BinaryFile(content,
                                  resourceName);
        }
    }

    public static BinaryFile fromPath(String filePath) throws IOException {
        if (Validator.isEmpty(filePath)) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        Path path = Path.of(filePath);
        byte[] fileContent = Files.readAllBytes(path);

        return new BinaryFile(fileContent,
                              path.getFileName().toString());
    }

    public BinaryFile(byte @NotNull [] bytes,
                      @NotNull String fileName) {
        this.fileContent = bytes;
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return Arrays.copyOf(fileContent,
                             fileContent.length);
    }

    public int getFileContentLength() {
        return fileContent.length;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        if (this.fileExtension == null && Validator.isStringNotEmpty(fileName) && fileName.contains(".")) {
            this.fileExtension = fileName.substring(fileName.lastIndexOf("."));
        }

        return fileExtension;
    }

    public void writeToDisk(String path) throws IOException {
        if (Validator.isEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        if (!path.endsWith("/")) {
            path += "/";
        }

        try (FileOutputStream fos = new FileOutputStream(path + fileName)) {
            fos.write(fileContent);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "BinaryFile{" +
               "fileName='" + fileName + '\'' +
               ", fileExtension='" + this.getFileExtension() + '\'' +
               ", fileContentLength=" + this.getFileContentLength() +
               '}';
    }

    public static class Builder {
        private byte[] fileContent;
        private String fileName;
        private String fileExtension;

        public Builder withFileContent(byte @NotNull [] fileContent) {
            this.fileContent = fileContent;
            return this;
        }

        public Builder withFileName(@NotNull String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        public BinaryFile build() {
            BinaryFile binaryFile = new BinaryFile(fileContent,
                                                   fileName);
            binaryFile.fileExtension = this.fileExtension; // Optional field
            return binaryFile;
        }
    }
}
