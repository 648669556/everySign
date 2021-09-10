package com.example.everysign;

import com.example.everysign.model.request.AddUserRequest;
import com.example.everysign.service.SignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

@SpringBootTest
public class SignServiceTest {
    @Autowired
    SignService signService;

    BigInteger userCode = new BigInteger("318102030243");

    public AddUserRequest mockAddRequest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserCode(userCode.toString());
        addUserRequest.setSignAddress("30.202685,120.248687");
        addUserRequest.setSignTime(LocalDateTime.of(2021, 9, 3, 10, 30, 23));
        userCode = userCode.add(BigInteger.ONE);
        return addUserRequest;
    }

    @Test
    public void test_00_save() {
        for (int i = 0; i < 1; i++) {
            signService.saveUser(mockAddRequest());
        }
    }

    @Test
    public void test_01_input() {
        signService.inputUserInfo();
    }
}
