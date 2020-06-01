package com.example.easyexcel.service;

import com.example.easyexcel.bean.UploadData;

import java.util.List;

/**
 * @desc    easyExcel接口
 * @auther huanghua
 * @create 2020-06-01 10:55
 */
public interface IEasyExcelManager<T> {

    /**
     * 存储上传的数据
     *
     * @param uploadDataList
     */
    void saveExcelDate(List<T> uploadDataList);

}
