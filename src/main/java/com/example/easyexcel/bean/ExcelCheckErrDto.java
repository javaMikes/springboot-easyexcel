package com.example.easyexcel.bean;

import lombok.Data;

/**
 * @desc    失败返回封装
 * @auther huanghua
 * @create 2020-06-01 10:29
 */
@Data
public class ExcelCheckErrDto<T> {

    private T t;

    private String errMsg;

    public ExcelCheckErrDto(){}

    public ExcelCheckErrDto(T t, String errMsg){
        this.t = t;
        this.errMsg = errMsg;
    }

}
