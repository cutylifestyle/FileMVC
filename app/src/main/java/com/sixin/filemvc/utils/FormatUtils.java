package com.sixin.filemvc.utils;

import android.text.TextUtils;

import java.io.File;

public class FormatUtils {

    /**
     * 获取文件格式名
     */
    public static String getFormatName(String fileName) {
        //去掉首尾的空格
        fileName = fileName.trim();
        String s[] = fileName.split("\\.");
        if (s.length >= 2) {
            return s[s.length - 1];
        }
        return "";
    }

    /**
     * 获取文件对应icon
     */
    public static int getFileIcon(String fileName) {
        File file = new File(fileName);
        if (file.isDirectory()) {
            return FormatEnum.FOLDER.ICON;
        }
        //获取扩展名并且全部转小写
        String extension = getFormatName(fileName).toLowerCase();
        if (TextUtils.isEmpty(extension)) {
            return FormatEnum.UNKNOWN.ICON;
        }
        FormatEnum format = FormatEnum.getFormat(extension);
        return format.ICON;
    }

    /**
     * 获取文件对应类型
     */
    public static String getFileType(String fileName) {
        //获取扩展名并且全部转小写
        String extension = getFormatName(fileName).toLowerCase();
        if (TextUtils.isEmpty(extension)) {
            return FormatEnum.FOLDER.TYPE;
        }
        FormatEnum format = FormatEnum.getFormat(extension);
        return format.TYPE;
    }

    /**
     * 通过文件获取icon
     */
    public static int getFileIcon(File file) {
        return getFileIcon(file.getName());
    }

    /**
     * 通过文件获取格式名
     */
    public static String getForamtName(File file) {
        return getFormatName(file.getName());
    }
}
