package me.jinkun.rds.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Description: 文件处理工具类！ <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
public class UtilFile {
    public static boolean mkDirs(String path) {
        delDirs(new File(path));
        File descFile = new File(path);
        return descFile.mkdirs();
    }

    public static boolean delDirs(String dir) {
        return delDirs(new File(dir));
    }

    public static boolean delDirs(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = delDirs(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static OutputStream getOutputStream(String path, String fileName) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(dir, fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }
}
