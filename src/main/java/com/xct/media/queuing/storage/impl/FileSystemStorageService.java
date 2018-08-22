package com.xct.media.queuing.storage.impl;

import com.xct.media.queuing.conf.StorageProperties;
import com.xct.media.queuing.storage.StorageService;
import com.xct.media.queuing.storage.exception.StorageException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by Chris on 2017/9/16.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 定义更路径
    private Path rootLocation;


    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }


    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("could not initialize storage", e);
        }
    }

    @Override
    public void removeAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Stream<Path> loadAll(String type) {
        logger.info("load type {}, all image", type);
        try {
            Path resolvePath = this.rootLocation.resolve(type);

            return Files.walk(resolvePath, 1)
                    .filter(path -> !path.equals(resolvePath))
                    .map(path -> resolvePath.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Stream<Path> loadFile(String fileName) {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.getFileName().equals(fileName))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("failed to read stored file.", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        return loadAsResource(2, filename);
    }

    @Override
    public Resource loadAsResource(int type, String filename) {
        String filePath = selfStorageDir(type);
        Path file = rootLocation.resolve(filePath).resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageException("could not read file: " + filename, e);
        }
    }

    @Override
    public String store(String filePath, MultipartFile file) {
        String filename = "";
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String originalFilename = file.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            int length = originalFilename.length();

            String suffix = originalFilename.substring(lastIndexOf, length);

            filename = originalFilename.substring(0, lastIndexOf) + "_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + suffix;
            if (filename.contains("..")) {
                throw new StorageException("Cannot store file with relative path outside current directory ");
            }
            Path storageChild = rootLocation.resolve(filePath);

            // 判断目录是否存在，不存在创建目录
            if (Files.notExists(storageChild)) {
                Files.createDirectory(storageChild);
            }
            //  写入文件
            Files.copy(file.getInputStream(), storageChild.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file ", e);
        }
        logger.info("upload file end name is --------------------------- {}", filename);
        return filename;
    }

    @Override
    public String storeBase64Image(int type, String base64String) {
        String filePath = selfStorageDir(type);
        String filename = "";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte = decoder.decodeBuffer(base64String);
            String formatDateStr = DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
            filename = RandomStringUtils.randomAlphanumeric(20) + formatDateStr + ".png";
            logger.info("print storage '*.png' , filename {}", filename);

            Path fileDirPath = rootLocation.resolve(filePath);
            try {
                if (!Files.exists(fileDirPath)) {
                    Files.createDirectories(fileDirPath);
                    Files.createFile(fileDirPath.resolve(filename));
                }
                // Files.copy(bis, storageChild.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                Files.write(fileDirPath.resolve(filename), imageByte, StandardOpenOption.CREATE);
                logger.info("file name storage file location.");
            } catch (IOException e) {
                throw new StorageException("Failed to store file ", e);
            }

        } catch (IOException e) {
            throw new StorageException("base64 to image storage exception.", e);
        }
        return filename;
    }

    private String selfStorageDir(int type) {
        String filePath = "";
        switch (type) {
            case 0:
                // 身份证头像
                filePath = "0";
                break;
            case 1:
                // 捕捉当前头像
                filePath = "1";
                break;
            case 2:
                // 业务员的头像
                filePath = "2";
                break;
            case 3:
                // 设置背景图
                filePath = "3";
                break;
            case 4:
                //
                filePath = "4";
                break;
            case 5:
                //
                filePath = "5";
                break;
            default:
                // do nothing ....
        }
        return filePath;
    }

}
