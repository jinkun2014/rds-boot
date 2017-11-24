package me.jinkun.rds.core.utils;

import java.io.File;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/15.
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
}
