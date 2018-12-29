package com.sixin.filemvc;

import android.os.Environment;

import com.sixin.filemvc.utils.FileUtils;

import java.io.File;
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

}
