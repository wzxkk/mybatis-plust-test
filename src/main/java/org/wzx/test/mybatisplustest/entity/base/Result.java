package org.wzx.test.mybatisplustest.entity.base;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: API统一返回类
 * @author: 鱼头
 * @time: 2020/5/21 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "API返回对象" , description = "本系统所有API返回格式均为此格式！" )
public class Result<T> implements Serializable {
    private T data;

    private String message;

    private int code;

}
