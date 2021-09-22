package com.example.everysign.service.impl;

import com.example.everysign.core.mapper.UserInfoDao;
import com.example.everysign.model.UserInfo;
import com.example.everysign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    @Transactional
    public Boolean saveUser(UserInfo userInfo) {
        if (StringUtils.isEmpty(userInfo.getUserId())) {
            log.warn("用户id不可为空");
            return false;
        }
        UserInfo userInfo1 = userInfoDao.selectByUserId(userInfo.getUserId());
        boolean result = false;
        if (userInfo1 == null) {
            result = userInfoDao.insert(userInfo) > 0;
        } else {
            result = userInfoDao.updateByUserId(userInfo) > 0;
        }
        return result;
    }

    @Override
    public List<UserInfo> listUser() {
        return userInfoDao.list();
    }
}
