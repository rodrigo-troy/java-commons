package com.rodrigotroy.commons.core.file;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * FileHandler is a utility class that provides methods for copying files.
 */
public final class FileHandler {
    private static final int BUFFER_SIZE = 8192;

    private FileHandler() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Copies a file from the specified input file path to the specified output file path.
     *
     * @param inFile  the path of the source file to copy from
     * @param outFile the path of the destination file to copy to
     * @throws IOException if an I/O error occurs during the copy operation, or if
     *                     the source and destination files are the same
     */
    public static void copy(@NotNull String inFile,
                            @NotNull String outFile) throws
                                                     IOException {
        File fileIN = new File(inFile);
        File fileOUT = new File(outFile);
        FileHandler.copy(fileIN,
                         fileOUT);
    }

    /**
     * Copies a file from the specified input file to the specified output file.
     *
     * @param inFile  the source file to copy from
     * @param outFile the destination file to copy to
     * @throws IOException if an I/O error occurs during the copy operation, or if
     *                     the source and destination files are the same
     */
    public static void copy(@NotNull File inFile,
                            @NotNull File outFile) throws
                                                   IOException {
        if (!inFile.exists()) {
            return;
        }

        if (inFile.getCanonicalPath().equals(outFile.getCanonicalPath())) {
            throw new IOException("NO SE PUEDE COPIAR EL ARCHIVO SOBRE SI MISMO");
        }

        if (inFile.isDirectory()) {
            throw new IOException("NO SE PUEDE COPIAR UN DIRECTORIO (" + inFile.getCanonicalPath() + ")");
        }

        if (outFile.isDirectory()) {
            throw new IOException("NO SE PUEDE COPIAR A UN DIRECTORIO (" + outFile.getCanonicalPath() + ")");
        }

        try (FileInputStream fis = new FileInputStream(inFile);
             InputStream in = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(outFile);
             OutputStream out = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer,
                          0,
                          bytesRead);
            }

        }
    }
}
