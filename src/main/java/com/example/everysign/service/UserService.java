package com.example.everysign.service;


import com.example.everysign.model.UserInfo;

import java.util.List;

public interface UserService {
    /**
     *  没有这个用户则新增有则修改
     */
    Boolean saveUser(UserInfo userInfo);

    /**
     * 查询用户列表
     */
    List<UserInfo> listUser();
}
