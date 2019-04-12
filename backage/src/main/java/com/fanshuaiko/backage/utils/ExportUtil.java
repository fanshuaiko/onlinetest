package com.fanshuaiko.backage.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @ClassName ExportUtil
 * @Description 文件下载工具类
 * @Author fanshuaiko
 * @Date 19-4-3 下午2:48
 * @Version 1.0
 **/
public class ExportUtil {
    public static void exportExcel(HttpServletResponse response, InputStream in,String name){
        try {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String fileName = URLEncoder.encode(name,"UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition","attachment;filename="+fileName);
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
