package com.rodrigotroy.commons.core.file;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    private static final int BUFFER_SIZE = 2048;
    private final byte[] zipContentAsBytes;
    private final String fileName;

    private ZipFile(String fileName,
                    byte[] zipContentAsBytes) {
        this.fileName = fileName;
        this.zipContentAsBytes = zipContentAsBytes;
    }

    public static @NotNull ZipFile createFromBytes(String fileName,
                                                   byte[] zipContentAsBytes) {
        return new ZipFile(fileName,
                           zipContentAsBytes);
    }

    public static @NotNull ZipFile createFromBytes(String fileName,
                                                   List<BinaryFile> binaryFiles) throws
                                                                                 IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            for (BinaryFile binaryFile : binaryFiles) {
                ZipEntry zipEntry = new ZipEntry(binaryFile.getFileName());
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(binaryFile.getFileContent());
                zipOutputStream.closeEntry();
            }

            zipOutputStream.finish();

            return new ZipFile(fileName,
                               byteArrayOutputStream.toByteArray());
        }
    }

    public byte[] getZipContentAsBytes() {
        return zipContentAsBytes == null ? new byte[0] : Arrays.copyOf(zipContentAsBytes,
                                                                       zipContentAsBytes.length);
    }

    public @NotNull Map<ZipEntry, byte[]> getZipContent() throws
                                                          IOException {
        Map<ZipEntry, byte[]> zipEntryMap = new HashMap<>();

        int count;
        try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(zipContentAsBytes))) {
            byte[] data = new byte[BUFFER_SIZE];
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    if (!zipEntry.isDirectory()) {
                        while ((count = zipInputStream.read(data,
                                                            0,
                                                            BUFFER_SIZE)) != -1) {
                            outputStream.write(data,
                                               0,
                                               count);
                        }
                    }

                    outputStream.flush();

                    zipEntryMap.put(zipEntry,
                                    outputStream.toByteArray());
                }
            }
        }

        return zipEntryMap;
    }

    public void writeToZipFile(String filePath) throws
                                                IOException {
        BinaryFile binaryFile = new BinaryFile(zipContentAsBytes,
                                               fileName);
        binaryFile.writeToDisk(filePath);
    }
}
