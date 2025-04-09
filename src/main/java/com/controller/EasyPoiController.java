package com.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.constant.ExcelConstant;
import com.manager.DataManager;
import com.model.excel.ExportGeneral;
import com.utils.EasyPoiExcelUtil;
import com.utils.ExcelStyleUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/easypoi")
public class EasyPoiController {

    @Resource
    private DataManager dataManager;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "type") int type) {
        // 导出最普通的
        if (1 == type) {
            exportGeneral(request, response);
        }
    }

    public void exportGeneral(HttpServletRequest request, HttpServletResponse response) {
        // 获取导出数据
        List<ExportGeneral> excelList = dataManager.getExportGeneral();
        // 设置名称
        String sheetName = "sheet";
        String excelName = "导出" + ExcelConstant.SUFFIX_XLSX;

        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName(sheetName);
        // exportParams.setStyle(ExcelStyleUtil.class);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExportGeneral.class, excelList);

        // 对导出完成的工作簿进行后处理，设置单元格颜色
        setScoreColors(workbook);

        // 下载
        String contentType = "application/octet-stream";
        EasyPoiExcelUtil.downLoadExcel(request, response, excelName, workbook, null, contentType);
    }

    // 后处理方法,专门设置颜色
    private static void setScoreColors(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        // 获取分数列的索引（根据表头查找）
        // int scoreColIndex = -1;
        // 表头行通常是第二行（索引1）
        // Row headerRow = sheet.getRow(1);

        /*for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && "数字".equals(cell.getStringCellValue())) {
                scoreColIndex = i;
                break;
            }
        }*/

        // 没找到分数列
        /*if (scoreColIndex == -1) {
            return;
        }*/

        // 创建红色样式
        CellStyle redStyle = workbook.createCellStyle();
        redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        redStyle.setAlignment(HorizontalAlignment.CENTER);
        redStyle.setBorderBottom(BorderStyle.THIN);
        redStyle.setBorderLeft(BorderStyle.THIN);
        redStyle.setBorderRight(BorderStyle.THIN);
        redStyle.setBorderTop(BorderStyle.THIN);

        // 从数据行开始遍历
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(3);
            if (Integer.parseInt(cell.getStringCellValue().trim()) < 5) {
                /*row.setRowStyle(redStyle);*/
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    cell = row.getCell(k);
                    cell.setCellStyle(redStyle);
                }
            }
            /*if (row != null) {
                Cell scoreCell = row.getCell(scoreColIndex);
                if (scoreCell != null) {
                    // 安全地获取分数值
                    int score;
                    try {
                        // 尝试直接获取数值
                        if (scoreCell.getCellType() == CellType.NUMERIC) {
                            score = (int) scoreCell.getNumericCellValue();
                        } else {
                            // 如果是字符串，则解析为整数
                            score = Integer.parseInt(scoreCell.getStringCellValue().trim());
                        }
                        // 设置单元格样式
                        if (score < 60) {
                            scoreCell.setCellStyle(redStyle);
                        } else if (score > 90) {
                            scoreCell.setCellStyle(greenStyle);
                        }
                    } catch (Exception e) {
                        // 转换失败，保持原样
                        *//*log.error("处理单元格时出错：", e);*//*
                    }
                }
            }*/
        }
    }

}