package com.example.easyexcel.validate;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.Set;


/**
 * @desc easyexcel验证
 * @auther huanghua
 * @create 2020-06-01 10:33
 */
public class EasyExcelValidateHelper {

    private EasyExcelValidateHelper() {
    }

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证实体类是否合法
     *
     * @param obj
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> String validateEntity(T obj) throws NoSuchFieldException {
        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && !set.isEmpty()) {
            for (ConstraintViolation<T> cv : set) {
                Field declaredField = obj.getClass().getDeclaredField(cv.getPropertyPath().toString());
                ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                result.append(annotation.value()[0] + cv.getMessage()).append(";");
            }
        }
        return result.toString();
    }

}
