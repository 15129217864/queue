package com.xct.media.queuing.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Chris on 2017/9/16.
 */
public interface StorageService {

    String FACE_DIR = "face";

    /**
     * 初始化纯粹路径
     */
    void init();

    /**
     * 删除所有文件
     * <p>
     * <ul>适用于测试环境<ul/>
     */
    void removeAll();

    /**
     * 加载指定目录下的所有文件
     *
     * @return
     */
    Stream<Path> loadAll(String type);

    /**
     * 加载图片
     *
     * @param fileName
     * @return
     */
    Stream<Path> loadFile(String fileName);

    /**
     * 加载文件资源
     *
     * @param filename
     * @return
     */
    Resource loadAsResource(String filename);

    /**
     * 加载文件资源
     *
     * @param filename
     * @return
     */
    Resource loadAsResource(int type, String filename);


    /**
     * 存储文件方法
     *
     * @param filePath
     * @param file
     * @return
     */
    String store(String filePath, MultipartFile file);


    /**
     * 存储base64 存储图片
     *
     * @param type
     * @param base64String
     * @return
     */
    String storeBase64Image(int type, String base64String);
}
