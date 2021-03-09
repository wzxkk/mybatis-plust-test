package org.wzx.test.mybatisplustest.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: 鱼头
 * @description: 服务基础接口
 * @since: 2020-06-28
 */
public interface BaseService<T> extends IService<T> {
    boolean checkDelete(String uuid);

    boolean checkRepeat(T entity);
}
