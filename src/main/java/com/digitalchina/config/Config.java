package com.digitalchina.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    @Value("${upload.path}")
    public String uploadPath;

    @Value("${project.context.path}")
    public String projectContextPath;// 项目路径

    @Value("${upload.file.folder}")
    public String uploadFileFolder;

    @Value("${upload.image.folder}")
    public String uploadImageFolder;

    @Value("${photo.download.path}")
    public String photoDownloadPath;


}
