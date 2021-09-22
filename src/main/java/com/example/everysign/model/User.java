package com.example.everysign.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 用户信息
 */
@Data
@ToString
public class User {

    private String userCode;

    private String signAddress;

    private LocalDateTime signTime;

    private String userName;

    private String signAddressName;

    private String userId;

    public User() {
    }

    public User(UserInfo userInfo) {
        BeanUtils.copyProperties(userInfo, this);
    }

    public static User Convert(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return user;
    }
}
