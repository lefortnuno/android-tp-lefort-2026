package com.example.iao_app;

import java.io.File;

public class FileItem {
    public File file;
    public boolean isParent;

    public FileItem(File file, boolean isParent) {
        this.file = file;
        this.isParent = isParent;
    }
}
