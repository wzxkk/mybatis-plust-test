package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.BourseInfo;
import org.wzx.test.mybatisplustest.mapper.BourseInfoMapper;
import org.wzx.test.mybatisplustest.service.IBourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 交易所信息服务实现类
 * @since 2021-03-09
 */
@Service
public class BourseInfoServiceImpl extends ServiceImpl<BourseInfoMapper, BourseInfo> implements IBourseInfoService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(BourseInfo entity) {
        return true;
    }
}