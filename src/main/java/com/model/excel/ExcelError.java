package com.model.excel;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author YeGuanWei
 * @since 2021-11-09
 */
@Data
public class ExcelError {

    // 行数
    private int row;

    // 消息
    private String msg;

}