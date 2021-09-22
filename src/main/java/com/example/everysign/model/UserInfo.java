package com.example.everysign.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    private String userCode;

    private String signAddress;

    private LocalDateTime signTime;

    private String userName;

    private String signAddressName;

    private String userId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "UserInfo{" +
                "\n userCode='" + userCode + '\'' +
                ", \n signAddress='" + signAddress + '\'' +
                ", \n signTime=" + signTime +
                ", \n userName='" + userName + '\'' +
                ", \n signAddressName='" + signAddressName + '\'' +
                ", \n userId=" + userId +
                '}';
    }
}