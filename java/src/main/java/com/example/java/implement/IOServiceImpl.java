package com.example.java.implement;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public final class IOServiceImpl {


    @Nullable
    public static boolean mkDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

    @Nullable
    public static OutputStream createFile(String path, String fileName) throws FileNotFoundException {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        return new FileOutputStream(String.format("%s/%s", path, fileName));
    }
}
