package com.bzvir.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bohdan.
 */
public class FileUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public static List<File> listFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if (root.exists()) {
            return listFiles(root);
        }
        return files;
    }

    private static List<File> listFiles(File root) {
        List<File> files = new LinkedList<>();
        if (!root.isDirectory() && root.exists()) {
            files.add(root);
            return files;
        }

        File[] subFiles = root.listFiles();
        for (File sub : subFiles) {
            files.addAll(listFiles(sub));
        }

        return files;
    }

    public static void printFileInfo(File file, StringBuffer sb) {
        sb.append(file.getParent());
        sb = getDate(file, sb);
        sb.append(file.getName())
                .append("\n");
    }

    private static StringBuffer getDate(File file, StringBuffer buffer) {
        FileTime creationTime;
        FileTime modifiedTime;
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
            buffer.append(" ::: error ::: ");
        }

        creationTime = attr.creationTime();
        modifiedTime = attr.lastModifiedTime();
        buffer
                .append(" TimeCre: ")
                .append(creationTime)
                .append(" TimeMod: ")
                .append(modifiedTime)
                .append(" ");
        return buffer;
    }
}
