package com.example.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.example.easyexcel.bean.ExcelCheckErrDto;
import com.example.easyexcel.service.IEasyExcelManager;
import com.example.easyexcel.validate.EasyExcelValidateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc easyExcel监听器
 * @auther huanghua
 * @create 2020-06-01 10:27
 */
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    //失败列表
    private List<ExcelCheckErrDto<T>> errList = new ArrayList<>();

    //存储上传的数据
    private List<T> list = new ArrayList<>();

    //处理逻辑service
    IEasyExcelManager easyExcelManager;

    public EasyExcelListener(IEasyExcelManager<T> easyExcelManager){
        this.easyExcelManager = easyExcelManager;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        String errMsg;
        try {
            //根据excel数据实体中的javax.validation + 正则表达式来校验excel数据
            errMsg = EasyExcelValidateHelper.validateEntity(data);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(errMsg)) {
            ExcelCheckErrDto excelCheckErrDto = new ExcelCheckErrDto(data, errMsg);
            errList.add(excelCheckErrDto);
        } else {
            list.add(data);
        }
        //每1000条处理一次
        if (list.size() > 1000) {
            //save to db
            easyExcelManager.saveExcelDate(list);
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("=========解析完成=========");
        //处理剩下的数据
        if (list.size() > 0) {
            easyExcelManager.saveExcelDate(list);
        }
    }

    /**
     * 获取错误信息
     *
     * @return
     */
    public List<ExcelCheckErrDto<T>> getErrList() {
        return errList;
    }

}
