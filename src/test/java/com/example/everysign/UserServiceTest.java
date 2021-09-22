package com.example.everysign;

import com.example.everysign.model.request.AddUserRequest;
import com.example.everysign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class UserServiceTest {
    @Autowired
    UserService userService;
    BigInteger userCode = new BigInteger("318102020732");

    public AddUserRequest mockAddRequest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserCode(userCode.toString());
        addUserRequest.setSignAddress("30.202685,120.248687");
        addUserRequest.setSignTime(LocalDateTime.of(2021, 9, 3, 10, 30, 23));
        return addUserRequest;
    }
}
