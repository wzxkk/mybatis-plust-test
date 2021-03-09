package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.TradeType;
import org.wzx.test.mybatisplustest.mapper.TradeTypeMapper;
import org.wzx.test.mybatisplustest.service.ITradeTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 交易类型信息服务实现类
 * @since 2021-03-09
 */
@Service
public class TradeTypeServiceImpl extends ServiceImpl<TradeTypeMapper, TradeType> implements ITradeTypeService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(TradeType entity) {
        return true;
    }
}