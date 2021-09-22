package com.example.everysign;

import com.example.everysign.model.request.AddUserRequest;
import com.example.everysign.service.impl.SignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SignServiceTest {

    @Autowired
    SignService signService;

    BigInteger userCode = new BigInteger("318102030243");

    public AddUserRequest mockAddRequest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserCode(userCode.toString());
        addUserRequest.setSignAddress("30.202685,120.248687");
        addUserRequest.setSignTime(LocalDateTime.of(2021, 9, 3, 10, 30, 23));
        addUserRequest.setUserId(userCode.toString());
        addUserRequest.setUserName("测试人员");
        addUserRequest.setSignAddressName("测试地点");
        userCode = userCode.add(BigInteger.ONE);
        return addUserRequest;
    }

    @Test
    public void test_00_save() {
        for (int i = 0; i < 1; i++) {
            boolean b = signService.saveUser(mockAddRequest());
            System.out.println(b);
        }
    }

    @Test
    public void test_01_input() {
        signService.inputUserInfo();
    }
}
