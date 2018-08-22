package com.xct.media.queuing.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/3/2 17:03.
 * @Description:
 */
public class Base64CodeToImg {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String uploadAndGetImgName(String base64Data, String prefix) {
        //保存图片名称
        StringBuffer img_n = new StringBuffer("");
        try {
            String dataPrix = "";
            String data = "";
            if (base64Data == null || "".equals(base64Data)) {
                log.info("upload fail,img base64 data is null");
            } else {
                String[] d = base64Data.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    log.info("upload fail,data is illegal");
                }
            }
            String suffix = "";
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } /*else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            }*/ else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            } else {
                log.info("upload fail,img type " + dataPrix + " is not jpg");
            }

            //进行解密
            byte[] result = Base64.decodeBase64(data);
            // 处理数据
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {
                    result[i] += 256;
                }
            }

            String cn = new PinyinTool().toPinYin(prefix, "", PinyinTool.Type.FIRSTUPPER);
            String tm = String.valueOf(System.currentTimeMillis());

            img_n = new StringBuffer().append(cn).append("-").append(tm).append(".jpg");
            log.info("img name is ----------------------------- {}", img_n.toString());
            //生成jpeg图片
            String imgFilePath = UploadPath.getUploadPath(UploadPath.C_File_DIR.getPath()) + img_n.toString();//新生成的图片
            //String imgFilePath = CreateXctFileUtils.createXctDefaultDirectory()+img_n.toString();//新生成的图片
            log.info("upload image file path {}", imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(result);
            out.flush();
            out.close();

            return img_n.toString();
        } catch (Exception e) {
            log.info("upload fail!");
        }
        return img_n.toString();
    }
}
