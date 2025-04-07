package com.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExportFlowUtil {

    public static void exportFlow(HttpServletResponse response, String filePath) {
        HttpURLConnection conn = null;
        try {
            ServletOutputStream out = response.getOutputStream();
            // 取得文件的后缀名
            String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
            URL url = new URL(filePath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream fis = conn.getInputStream();// 通过输入流获取数据
            byte[] buffer = readInputStream(fis);
            // 设置response的Header
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((filename).getBytes("UTF-8")));
            response.setHeader("Content-Length", "" + buffer.length);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/octet-stream");
            out.write(buffer);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static byte[] readInputStream(InputStream inputstream) {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        try {
            while ((len = inputstream.read(buffer)) != -1) {
                outputstream.write(buffer, 0, len);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            outputstream.close();
            inputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputstream.toByteArray();
    }

}
