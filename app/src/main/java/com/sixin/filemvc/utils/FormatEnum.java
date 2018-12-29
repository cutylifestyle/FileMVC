package com.sixin.filemvc.utils;


import com.sixin.filemvc.R;

public enum FormatEnum {
    //文件夹
    FOLDER("folder",R.drawable.ic_work_light_green_500),

    //图片格式
    IMG("img",R.drawable.ic_photo_green_500_24dp,"jpg", "jpeg", "gif", "png", "bmp", "tiff"),

    //文本格式
    TXT("txt", R.drawable.ic_insert_drive_file_blue_500, "txt"),

    //文档格式
    WORD("word",R.drawable.ic_insert_drive_file_blue_500, "docx", "dotx", "doc", "dot", "pagers"),

    //电子表格
    EXCEL("excel", R.drawable.ic_insert_drive_file_blue_500, "xls", "xlsx", "xlt", "xltx"),

    //ppt
    PPT("ppt", R.drawable.ic_insert_drive_file_blue_500, "ppt", "pptx"),

    //pdf
    PDF("pdf", R.drawable.ic_copyright_blue_600, "pdf"),

    //音频格式
    MP3("mp3", R.drawable.ic_copyright_blue_600, "mp3", "wav", "wma"),

    //视频格式
    VIDEO("video", R.drawable.ic_copyright_blue_600, "avi", "flv", "mpg", "mpeg", "mp4", "3gp", "mov", "rmvb", "mkv"),

    //网页格式
    HTML("html", R.drawable.ic_copyright_blue_600, "html"),

    //cad
    CAD("cad", R.drawable.ic_copyright_blue_600, "dwg","dxf","dwt"),

    //ps
    PS("ps", R.drawable.ic_copyright_blue_600, "psd", "pdd"),

    //max
    MAX3D("3DMax", R.drawable.ic_cloud_queue_red_500, "max"),

    //压缩包
    ZIP("zip", R.drawable.ic_cloud_queue_red_500, "zip", "jar", "rar", "7z"),

    //未知格式
    UNKNOWN("unknown", R.drawable.ic_insert_drive_file_blue_500);

    public String TYPE;
    public int ICON;
    public String[] FORMATS;

    FormatEnum(String type,int icon,String ... formats){
        this.TYPE = type;
        this.ICON = icon;
        this.FORMATS = formats;
    }

    /**
     * 通过文件类型获取对应枚举
     *
     * @param extension 文件扩展名
     * @return 文件对应的枚举信息，如果没有，返回未知
     */
    public static FormatEnum getFormat(String extension) {
        for (FormatEnum format : FormatEnum.values()) {
            for (String extend : format.FORMATS) {
                if (extend.equalsIgnoreCase(extension)) {
                    return format;
                }
            }
        }
        return UNKNOWN;
    }
}
