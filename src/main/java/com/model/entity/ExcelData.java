package com.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExcelData {

    private int code;

    private List<ListData> list;

    @Data
    public static class ListData {

        private String id;

        private String name;

        private String text;

        private int numeral;

        private Long timeStamp;

        private Date date;

    }

}