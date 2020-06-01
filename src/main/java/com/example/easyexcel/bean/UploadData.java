package com.example.easyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @desc   接收数据实体类
 * @auther huanghua
 * @create 2020-06-01 10:26
 */
@Data
@TableName("upload_data")
public class UploadData {

    /**
     * id
     */
    @TableId
    @TableField(value = "id")
    private int id;

    /**
     * 姓名
     */
    @ExcelProperty(index = 0)
    @NotEmpty(message="姓名不能为空")
    @TableField(value = "name")
    private String name;

    /**
     * 性别
     */
    @ExcelProperty(index = 1)
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 手机号码
     */
    @ExcelProperty(index = 2)
    @TableField(value = "phone")
    @Pattern(regexp="^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$", message="手机号格式错误")
    private String phone;

    /**
     * 出生日期
     */
    @ExcelProperty(index = 3)
    @TableField(value = "birth_day")
    private Date birthDay;

    /**
     * 家庭住址
     */
    @ExcelProperty(index = 4)
    @TableField(value = "address")
    private String address;

}
