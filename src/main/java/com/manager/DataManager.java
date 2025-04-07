package com.manager;

import com.alibaba.fastjson.JSON;
import com.model.entity.ExcelData;
import com.model.excel.ExportGeneral;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataManager {

    /**
     * 原始数据
     **/
    public ExcelData getData() {
        String str = "{\"code\":\"1\",\"list\":[{\"id\":\"1\",\"name\":\"测试数据1\",\"text\":\"文本-1\",\"numeral\":1,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"2\",\"name\":\"测试数据2\",\"text\":\"文本-2\",\"numeral\":2,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"3\",\"name\":\"测试数据3\",\"text\":\"文本-3\",\"numeral\":3,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"4\",\"name\":\"测试数据4\",\"text\":\"文本-4\",\"numeral\":4,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"5\",\"name\":\"测试数据5\",\"text\":\"文本-5\",\"numeral\":5,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"6\",\"name\":\"测试数据6\",\"text\":\"文本-6\",\"numeral\":6,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"7\",\"name\":\"测试数据7\",\"text\":\"文本-7\",\"numeral\":7,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"8\",\"name\":\"测试数据8\",\"text\":\"文本-8\",\"numeral\":8,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"9\",\"name\":\"测试数据9\",\"text\":\"文本-9\",\"numeral\":9,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"10\",\"name\":\"测试数据10\",\"text\":\"文本-10\",\"numeral\":10,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"},{\"id\":\"11\",\"name\":\"测试数据11\",\"text\":\"文本-11\",\"numeral\":11,\"timeStamp\":\"1743995346053\",\"date\":\"2025-04-07 11:09:06\"}]}";
        // 解析字符串
        return JSON.parseObject(str, ExcelData.class);
    }

    /**
     * 获取导出数据 - 普通
     **/
    public List<ExportGeneral> getExportGeneral() {
        List<ExportGeneral> list = new ArrayList<>();
        ExportGeneral exportGeneral;

        ExcelData data = getData();
        for (ExcelData.ListData entity : data.getList()) {
            exportGeneral = new ExportGeneral();
            exportGeneral.setId(entity.getId());
            exportGeneral.setName(entity.getName());
            exportGeneral.setText(entity.getText());
            exportGeneral.setNumeral(entity.getNumeral());
            exportGeneral.setTimeStamp(entity.getTimeStamp());
            exportGeneral.setDate(entity.getDate());
            list.add(exportGeneral);
        }
        return list;
    }

}
