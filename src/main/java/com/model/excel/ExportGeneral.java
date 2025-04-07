package com.model.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class ExportGeneral {

    @Excel(name = "ID")
    private String id;

    @Excel(name = "名称")
    private String name;

    @Excel(name = "文本")
    private String text;

    @Excel(name = "数字")
    private Integer numeral;

    @Excel(name = "时间戳")
    private Long timeStamp;

    @Excel(name = "时间")
    private Date date;

}
