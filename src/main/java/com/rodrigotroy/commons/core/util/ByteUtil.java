package com.rodrigotroy.commons.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 01-08-2024
 * Time: 12:26
 */
public final class ByteUtil {
    private static final int BUFFER_SIZE = 4096;

    private ByteUtil() {
        // Utility class, no instances allowed
    }

    /**
     * Converts the given InputStream to a byte array.
     *
     * @param inputStream the input stream to convert
     * @return a byte array representation of the input stream
     * @throws IOException if an I/O error occurs
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,
                                   0,
                                   bytesRead);
            }

            return outputStream.toByteArray();
        }
    }
}
