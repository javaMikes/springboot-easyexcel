package com.example.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.example.easyexcel.bean.ExcelCheckResult;
import com.example.easyexcel.bean.UploadData;
import com.example.easyexcel.listener.EasyExcelListener;
import com.example.easyexcel.service.IEasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @desc   读写excel接口
 * @auther huanghua
 * @create 2020-06-01 10:16
 */
@Controller
public class EasyExcelController {

    @Autowired
    private IEasyExcelService easyExcelService;

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link com.example.easyexcel.listener.EasyExcelListener}
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        // excel导入监听器
        EasyExcelListener easyExcelListener = new EasyExcelListener(easyExcelService);
        EasyExcel.read(file.getInputStream(), UploadData.class, easyExcelListener).sheet().doRead();
        //获取校验不合法信息
        List<ExcelCheckResult<UploadData>> errList = easyExcelListener.getErrList();
        return errList.toString();
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), UploadData.class).sheet("模板").doWrite(easyExcelService.findUploadDataList());
    }

}
