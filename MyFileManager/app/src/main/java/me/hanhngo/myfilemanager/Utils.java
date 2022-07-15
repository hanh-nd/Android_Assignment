package me.hanhngo.myfilemanager;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<File> getListFiles(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            return new ArrayList<>(Arrays.asList(list));
        }
        return null;
    }

    public static FileModel fromFileToFileModel(File file) {
        return new FileModel(file.getName(), file.getAbsolutePath(), getFileType(file));
    }

    public static FileType getFileType(File file) {
        if (file.isDirectory()) {
            return FileType.DIRECTORY;
        }
        String extension;
        int index = file.getName().lastIndexOf(".");
        if (index > 0) {
            extension = file.getName().substring(index + 1);
        } else {
            return null;
        }

        if (extension.equals("jpg")
                || extension.equals("jpeg")
                || extension.equals("webp")
                || extension.equals("png")
                || extension.equals("bmp")
                || extension.equals("tiff")
        ) {
            return FileType.IMAGE;
        }

        if (extension.equals("txt")) {
            return FileType.TEXT;
        }

        return null;

    }
}
