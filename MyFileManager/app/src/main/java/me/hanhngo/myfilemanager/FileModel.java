package me.hanhngo.myfilemanager;

public class FileModel {
    private String fileName;
    private String absolutePath;
    private FileType type;

    public FileModel(String fileName, String absolutePath, FileType type) {
        this.fileName = fileName;
        this.absolutePath = absolutePath;
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public String getDirectoryPath() {
        if (type == FileType.DIRECTORY) {
            return absolutePath;
        }
        return absolutePath.replace(fileName, "");
    }

    public String getExtension() {
        if (type == FileType.DIRECTORY) {
            return "";
        }
        return fileName.split("\\.")[1];
    }
}
