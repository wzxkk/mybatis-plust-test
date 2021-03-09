package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.EntrustType;
import org.wzx.test.mybatisplustest.mapper.EntrustTypeMapper;
import org.wzx.test.mybatisplustest.service.IEntrustTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 委托类型服务实现类
 * @since 2021-03-09
 */
@Service
public class EntrustTypeServiceImpl extends ServiceImpl<EntrustTypeMapper, EntrustType> implements IEntrustTypeService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(EntrustType entity) {
        return true;
    }
}