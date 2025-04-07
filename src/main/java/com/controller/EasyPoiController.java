package com.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.constant.ExcelConstant;
import com.manager.DataManager;
import com.model.excel.ExportGeneral;
import com.utils.EasyPoiExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void Export(HttpServletRequest request, HttpServletResponse response) {
        // 导出最普通的
        exportGeneral(request, response);
    }

    public void exportGeneral(HttpServletRequest request, HttpServletResponse response) {
        // 获取导出数据
        List<ExportGeneral> excelList = dataManager.getExportGeneral();
        // 设置名称
        String sheetName = "sheet";
        String excelName = "问答记录" + ExcelConstant.SUFFIX_XLS;

        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName(sheetName);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExportGeneral.class, excelList);
        // 下载
        String contentType = "application/octet-stream";
        EasyPoiExcelUtil.downLoadExcel(request, response, excelName, workbook, null, contentType);
    }

}