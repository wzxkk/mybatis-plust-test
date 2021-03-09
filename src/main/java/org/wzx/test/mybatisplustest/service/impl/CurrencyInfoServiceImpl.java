package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.CurrencyInfo;
import org.wzx.test.mybatisplustest.mapper.CurrencyInfoMapper;
import org.wzx.test.mybatisplustest.service.ICurrencyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 币种信息服务实现类
 * @since 2021-03-09
 */
@Service
public class CurrencyInfoServiceImpl extends ServiceImpl<CurrencyInfoMapper, CurrencyInfo> implements ICurrencyInfoService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(CurrencyInfo entity) {
        return true;
    }
}