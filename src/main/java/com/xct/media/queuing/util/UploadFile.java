package com.xct.media.queuing.util;

import com.xct.media.queuing.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/1/26 17:26.
 * @Description:
 */

public class UploadFile {

    //法院信息中的上传后的图片名称保存到数列中
    public static List<String> court_imgs = new ArrayList<String>();
    //数组下标
    public static int index = 0;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StorageService storageService;

    public String uploadCourtImage(MultipartFile multipartFile) {
        String path = UploadPath.getUploadPath(UploadPath.C_File_DIR.getPath());
        logger.info("file upload path is ------------------------------- {}", path);
        //上传文件
        String cimg = storageService.store(path, multipartFile);
        logger.info("get upload file end name is ------------------------------- {}", cimg);
        court_imgs.add(index, cimg);
        index++;
        String show_path = "/court_img/" + cimg;
        logger.info("web show img path ---------------------------- {}", show_path);
        return show_path;
    }
}
