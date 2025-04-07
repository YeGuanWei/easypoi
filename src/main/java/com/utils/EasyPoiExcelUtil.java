package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author YeGuanWei
 */
public class EasyPoiExcelUtil {

    private static final Logger logger = LogManager.getLogger(EasyPoiExcelUtil.class);

    public static void downLoadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Workbook workbook, String encoding, String contentType) {
        try {
            if (StringUtil.isNotBlank(encoding)) {
                response.setCharacterEncoding(encoding);
            }
            response.setHeader("Content-Type", contentType);
            // response.setHeader("Content-Disposition", "attachment;filename=" + getEncodeFileName(request, fileName));
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error("导出失败", e.getMessage());
        }
    }

    public static void downLoadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Workbook workbook) {
        String contentTyp = "application/vnd.ms-excel;charset=UTF-8";
        String encoding = "UTF-8";
        downLoadExcel(request, response, fileName, workbook, encoding, contentTyp);
    }

    private static String getEncodeFileName(HttpServletRequest request, String fileName) throws Exception {
        String userAgent = request == null ? null : request.getHeader("USER-AGENT");
        if (StringUtil.isNotBlank(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("Firefox".toLowerCase())) {
                fileName = new String(fileName.getBytes(), "ISO-8859-1");
            } else {
                fileName = urlEncode(fileName, "UTF-8");
            }
        } else {
            fileName = urlEncode(fileName, "UTF-8");
        }

        return fileName;
    }

    private static String urlEncode(String str, String charset) throws UnsupportedEncodingException {
        return !StringUtil.isEmpty(str) ? URLEncoder.encode(str, charset) : str;
    }

}