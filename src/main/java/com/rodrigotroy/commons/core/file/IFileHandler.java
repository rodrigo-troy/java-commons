package com.rodrigotroy.commons.core.file;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 2/18/13
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IFileHandler {
    FileType getType();

    InputStream getInputStream();
}
