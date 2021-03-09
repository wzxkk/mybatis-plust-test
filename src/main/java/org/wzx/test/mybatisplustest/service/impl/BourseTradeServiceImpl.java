package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.BourseTrade;
import org.wzx.test.mybatisplustest.mapper.BourseTradeMapper;
import org.wzx.test.mybatisplustest.service.IBourseTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 交易所所拥有的交易类型服务实现类
 * @since 2021-03-09
 */
@Service
public class BourseTradeServiceImpl extends ServiceImpl<BourseTradeMapper, BourseTrade> implements IBourseTradeService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(BourseTrade entity) {
        return true;
    }
}