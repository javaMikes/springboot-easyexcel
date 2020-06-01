package com.example.easyexcel.service;

import com.example.easyexcel.bean.UploadData;

import java.util.List;

/**
 * @desc    easyExcel接口
 * @auther huanghua
 * @create 2020-06-01 10:43
 */
public interface IEasyExcelService extends IEasyExcelManager<UploadData> {

    /**
     * 查询数据
     *
     * @return
     */
    List<UploadData> findUploadDataList();

}
