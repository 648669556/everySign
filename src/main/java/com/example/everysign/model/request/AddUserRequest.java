package com.example.everysign.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AddUserRequest {
    @NotNull(message = "userCode不可为空")
    private String userCode;
    @NotNull(message = "signAddress不可为空")
    private String signAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "signTime不可为空")
    private LocalDateTime signTime;
    @NotNull(message = "userName不可为空")
    private String userName;
    @NotNull(message = "signAddressName不可为空")
    private String signAddressName;
    @NotNull(message = "userId不可为空")
    private String userId;
}
