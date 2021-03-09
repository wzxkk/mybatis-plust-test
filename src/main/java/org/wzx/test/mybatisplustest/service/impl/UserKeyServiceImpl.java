package org.wzx.test.mybatisplustest.service.impl;

import org.wzx.test.mybatisplustest.entity.UserKey;
import org.wzx.test.mybatisplustest.mapper.UserKeyMapper;
import org.wzx.test.mybatisplustest.service.IUserKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 鱼头
 * @description: 用户key信息服务实现类
 * @since 2021-03-09
 */
@Service
public class UserKeyServiceImpl extends ServiceImpl<UserKeyMapper, UserKey> implements IUserKeyService {

    @Override
    public boolean checkDelete(String uuid) {
        return true;
    }

    @Override
    public boolean checkRepeat(UserKey entity) {
        return true;
    }
}