package com.xct.media.queuing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @Author: YouGX.
 * @Date: Create in 2017/8/12 12:35.
 * @Description:
 */
public enum UploadPath {

    C_File_DIR("/image/");

    private static Logger logger = LoggerFactory.getLogger(UploadPath.class);
    private String path;

    UploadPath(String path) {
        this.path = path;
    }

    public static String getUploadPath(String p) {
        p = "/static" + p;
        Resource resource = new ClassPathResource(p);
        String path = "";
        try {
            path = resource.getURL().toString().replace("file:/", "");
        } catch (IOException e) {
            logger.error("--------------------------------- path is error!");
            e.printStackTrace();
        }
        return path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
