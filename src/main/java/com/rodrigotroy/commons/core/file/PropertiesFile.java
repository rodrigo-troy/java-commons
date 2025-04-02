package com.rodrigotroy.commons.core.file;

import com.rodrigotroy.commons.core.util.ByteUtil;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

public final class PropertiesFile {
    private final @NotNull Properties properties;

    public PropertiesFile(byte[] bytes) throws IOException {
        this.properties = loadProperties(bytes);
    }

    public static PropertiesFile fromResourceAsStream(String resourceName) throws IOException {
        try (InputStream inputStream = PropertiesFile.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }

            return new PropertiesFile(ByteUtil.toByteArray(inputStream));
        }
    }

    public static PropertiesFile fromCurrentDirectory(String resourceName) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Path.of(resourceName))) {
            return new PropertiesFile(ByteUtil.toByteArray(inputStream));
        }
    }

    private @NotNull Properties loadProperties(byte[] bytes) throws IOException {
        Properties configProperties = new Properties();

        try (InputStream is = new ByteArrayInputStream(bytes)) {
            configProperties.load(is);
        }

        return configProperties;
    }

    public @NotNull Optional<String> getProperty(String key) {
        return Optional.ofNullable(this.properties.getProperty(key));
    }

    public @NotNull Properties getProperties() {
        Properties copy = new Properties();
        copy.putAll(this.properties);
        return copy;
    }
}
