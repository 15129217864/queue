package com.xct.media.queuing.util;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;

/**
 * 名称:DESPlusUtil工具类
 * 功能:修改系统相关文件的读写权限
 * 开启VNC服务
 * 时间:2013-06-21
 *
 * @author 陈庭财
 */
public class DESPlusUtil {
    // 加密密匙
    private static String strDefaultKey = "vvliveweb";
    private static DESPlusUtil ins = new DESPlusUtil();
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    /**
     * 默认构造方法，使用默认密钥
     */
    private DESPlusUtil() {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     */
    private DESPlusUtil(String strKey) {
        try {
            Key key = getKey(strKey.getBytes());

            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);

            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DESPlusUtil Get() {
        return ins;
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     */
    private static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     */
    private static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrOut = null;
        try {
            byte[] arrB = strIn.getBytes();
            int iLen = arrB.length;

            // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
            arrOut = new byte[iLen / 2];
            for (int i = 0; i < iLen; i = i + 2) {
                String strTmp = new String(arrB, i, 2);
                arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
            }
        } catch (Exception e) {
        }
        return arrOut;
    }

    @SuppressWarnings("unused")
    private static String loadFileAsString(String paramString) throws IOException {
        StringBuilder localStringBuffer = new StringBuilder(1000);
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
        char[] arrayofChar = new char[1024];
        while (true) {
            int i = localBufferedReader.read(arrayofChar);
            if (i == -1) {
                localBufferedReader.close();
                return localStringBuffer.toString();
            }
            localStringBuffer.append(String.valueOf(arrayofChar, 0, i));
        }
    }

    public static String transMac(String Mac) {
        String result = "";
        if (Mac.length() > 1) {
            Mac = Mac.replaceAll(" ", "");
            result = "";
            String[] tmp = Mac.split(":");
            for (int i = 0; i < tmp.length; ++i) {
                result += tmp[i];
            }
        }
        return result;
    }

    @SuppressWarnings("unused")
    private static void macCodAddress(String path, String str, StringBuffer buffer) {
        try {
            Process pp = Runtime.getRuntime().exec(path);
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    String macSerial = str.trim().toUpperCase();// 去空格
                    String macs[] = macSerial.split(":");
                    for (int i = 0; i < macs.length; i++) {
                        buffer.append(macs[i]);
                    }
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 加密字节数组
     */
    private byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 加密字符串
     */
    public String encrypt(String strIn) {
        try {
            return byteArr2HexStr(encrypt(strIn.getBytes("GBK")));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密字节数组
     */
    private byte[] decrypt(byte[] arrB) {
        try {
            return decryptCipher.doFinal(arrB);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解密字符串
     */
    public String decrypt(String strIn) {
        String str = "";
        try {
            str = new String(decrypt(hexStr2ByteArr(strIn)), "GBK");
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     */

    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }
}