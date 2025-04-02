package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;

public final class MimeTypeHandler {
    private MimeTypeHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static @NotNull String getMime(@NotNull String ext) {
        if (Validator.isEmpty(ext)) {
            return "text/plain";
        }

        if (ext.trim().indexOf("pdf") > 0) {
            return "application/pdf";
        } else if (ext.trim().indexOf("xls") > 0) {
            return "application/excel";
        } else if (ext.trim().indexOf("doc") > 0) {
            return "application/msword";
        } else if (ext.trim().indexOf("gif") > 0) {
            return "image/gif";
        } else if (ext.trim().indexOf("xml") > 0) {
            return "application/xml";
        } else if (ext.trim().indexOf("jpg") > 0) {
            return "image/jpeg";
        } else if (ext.trim().indexOf("jpeg") > 0) {
            return "image/jpeg";
        } else if (ext.trim().indexOf("png") > 0) {
            return "image/png";
        } else if (ext.trim().indexOf("ppt") > 0) {
            return "application/powerpoint";
        } else {
            return "text/plain";
        }
    }
}
