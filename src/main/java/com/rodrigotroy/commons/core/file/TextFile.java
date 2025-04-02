package com.rodrigotroy.commons.core.file;

import com.rodrigotroy.commons.core.util.ByteUtil;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFile {
    private final byte[] fileContent;
    private final String fileName;
    private final Charset charset;

    public TextFile(byte[] content,
                    @NotNull String fileName,
                    @NotNull Charset charset) {
        this.fileContent = content;
        this.fileName = fileName;
        this.charset = charset;
    }

    public static TextFile fromCurrentDirectory(@NotNull String resourceName,
                                                @NotNull Charset charset) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Path.of(resourceName))) {
            byte[] content = ByteUtil.toByteArray(inputStream);

            return TextFile.fromByte(content,
                                     resourceName,
                                     charset);
        }
    }

    public static TextFile fromResourceAsStream(@NotNull String resourceName,
                                                @NotNull Charset charset) throws IOException {
        try (InputStream inputStream = PropertiesFile.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }

            byte[] content = ByteUtil.toByteArray(inputStream);

            return TextFile.fromByte(content,
                                     resourceName,
                                     charset);
        }
    }

    public static TextFile fromByte(byte[] content,
                                    @NotNull String fileName,
                                    Charset charset) {
        if (Validator.isEmpty(content)) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        if (Validator.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        return new TextFile(content,
                            fileName,
                            charset);
    }

    public static TextFile fromString(@NotNull String content,
                                      @NotNull String fileName,
                                      Charset charset) {
        if (Validator.isEmpty(content)) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        if (Validator.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        return new TextFile(content.getBytes(charset),
                            fileName,
                            charset);
    }

    public static TextFile fromPath(Path path,
                                    Charset charset) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        List<String> strings = Files.readAllLines(path,
                                                  charset);

        return new TextFile(String.join("\n",
                                        strings).getBytes(charset),
                            path.getFileName().toString(),
                            charset);
    }

    public String getFileContent() {
        return new String(fileContent,
                          charset);
    }

    public int getFileContentLength() {
        return (new String(fileContent,
                           charset)).length();
    }

    public String getFileName() {
        return fileName;
    }

    public byte @NotNull [] getFileContentAsBytes() {
        return fileContent;
    }

    public void writeToDisk(Path path) throws IOException {
        if (Validator.isEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        if (!path.toString().endsWith(File.separator)) {
            path = path.resolve("");
        }

        File file = new File(path.resolve(fileName).toString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,
                                                                       charset))) {
            writer.write(new String(fileContent,
                                    charset));
        }
    }
}
