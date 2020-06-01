package com.example.easyexcel.service.impl;

import com.example.easyexcel.bean.UploadData;
import com.example.easyexcel.mapper.UploadDataMapper;
import com.example.easyexcel.service.IEasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc    easyExcel接口实现
 * @auther huanghua
 * @create 2020-06-01 10:45
 */
@Service
public class EasyExcelServiceImpl implements IEasyExcelService {

    @Autowired
    UploadDataMapper uploadDataMapper;

    /**
     * 存储上传的数据
     *
     * @param uploadDataList
     */
    @Override
    public void saveExcelDate(List<UploadData> uploadDataList) {
        // TODO save to db
        System.out.println("save to db");
        uploadDataList.forEach(uploadData -> {
            uploadDataMapper.insert(uploadData);
        });
    }

    /**
     * 查询数据
     *
     * @return
     */
    @Override
    public List<UploadData> findUploadDataList() {
        return uploadDataMapper.selectByMap(null);
    }
}
