/*
 * Copyright 2015-2017 XCT group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xct.media.queuing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * xct-api-court
 *
 * @author yuChao
 * @Date 2017年2月20日-下午9:35:20
 * @Desc 创建xct文件
 * @Since 1.8
 */
public class CreateXctFileUtils {

    private static final String BASE_DIR = "user.home";

    private static final String DEFAULT_DIRECTORY = ".xct";

    private static Logger logger = LoggerFactory.getLogger(CreateXctFileUtils.class);

    /**
     * 创建默认文件夹
     *
     * @return
     */
    public static String createXctDefaultDirectory() {
        String baseUrl = System.getProperty(BASE_DIR);
        StringBuilder fileUrl = new StringBuilder(baseUrl);
        fileUrl.append(File.separatorChar);
        fileUrl.append(DEFAULT_DIRECTORY);
        File createFile = new File(fileUrl.toString());
        if (!createFile.exists()) {
            createFile.mkdirs();
        }
        return createFile.getAbsolutePath();
    }

    /**
     * 获取文件路径
     *
     * @param fileName
     * @return
     */
    public static File getFileUrl(String fileName) {
        String defaultDir = createXctDefaultDirectory();
        StringBuilder builder = new StringBuilder(defaultDir);
        builder.append(File.separatorChar);
        builder.append(fileName);
        return new File(builder.toString());
    }

    /**
     * 写文件
     *
     * @param fileName
     * @param writeContent
     */
    public static boolean writeXctFile(String fileName, String writeContent) {
        boolean falg = false;
        File file = getFileUrl(fileName);
        logger.info("write {} contents {}", fileName, writeContent);
        // 写文件
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(writeContent.getBytes());
            falg = true;
            fos.close();
        } catch (IOException e) {
            falg = false;
            logger.error("write " + fileName + " content {} error info {}", writeContent, e.getMessage());
        }
        return falg;
    }
}
