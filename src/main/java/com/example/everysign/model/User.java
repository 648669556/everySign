package com.example.everysign.model;

import lombok.Data;
import lombok.ToString;

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

}
