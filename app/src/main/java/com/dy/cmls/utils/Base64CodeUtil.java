package com.dy.cmls.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by NIDAYE on 2018/1/23.
 */

public class Base64CodeUtil {

    public static String doCode(File file) {
        // 对图像进行base64编码
        String imgBase64 = "";
        try {
            byte[] content = new byte[(int) file.length()];
            FileInputStream finputstream = new FileInputStream(file);
            finputstream.read(content);
            finputstream.close();
            imgBase64 = new String(Base64.encodeBase64(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgBase64;
    }

    //打Log
    public void showLog(String info, String content) {
        LogUtil.e("", info + content);
    }
}
