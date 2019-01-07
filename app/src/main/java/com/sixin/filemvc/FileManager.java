package com.sixin.filemvc;

import android.os.Environment;

import com.sixin.filemvc.utils.FileUtils;
import com.sixin.filemvc.utils.FormatUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<File> readFiles() {
        List<File> files = null;
        String dirPath = Environment.getExternalStorageDirectory() + File.separator + "test";
        File fileDir = new File(dirPath);
        boolean isDir = FileUtils.isDir(fileDir);
        if(!isDir){
            return files;
        }else{
           return FileUtils.listFilesInDir(fileDir);
        }
    }

    public boolean deleteFile(File file) {
        return FileUtils.deleteFile(file);
    }

    public List<File> readFilesByFormatName(String formatName) {
        //TODO 代码复用的问题
        List<File> files = null;
        String dirPath = Environment.getExternalStorageDirectory() + File.separator + "test";
        File fileDir = new File(dirPath);
        boolean isDir = FileUtils.isDir(fileDir);
        if(!isDir){
            return files;
        }else{
            files = FileUtils.listFilesInDirWithFilter(fileDir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return FormatUtils.getForamtName(pathname).equalsIgnoreCase(formatName);
                }
            });
        }
        return files;
    }
}
