package com.example.io.serviceImpl;

import com.example.io.service.FileService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class FileServiceImpl implements FileService {

    @Nullable
    public Boolean mkDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

    @Nullable
    public OutputStream createFile(String path, String fileName) throws FileNotFoundException {
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
