package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 11/3/17
 * Time: 13:23
 */
public final class Base64Util {
    private Base64Util() {
        throw new IllegalStateException("Utility class");
    }

    public static String encodeByteToString(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    public static byte[] decodeStringToByte(String src) {
        return Base64.getDecoder().decode(src);
    }

    public static @NotNull String encode(@NotNull String text,
                                         boolean urlSafe) {
        Base64.Encoder encoder = urlSafe ? Base64.getUrlEncoder() : Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(text.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes,
                          StandardCharsets.UTF_8);
    }

    public static @NotNull String decode(@NotNull String text,
                                         boolean urlSafe) {
        Base64.Decoder decoder = urlSafe ? Base64.getUrlDecoder() : Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(text.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes,
                          StandardCharsets.UTF_8);
    }
}
