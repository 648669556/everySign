package com.example.everysign.controller;

import com.example.everysign.model.request.AddUserRequest;
import com.example.everysign.service.impl.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("sign")
@Validated
public class SignController {
    @Autowired
    SignService signService;

    @PostMapping("save")
    public Boolean saveUser(@RequestBody @Valid AddUserRequest addUserRequest) {
        signService.saveUser(addUserRequest);
        return true;
    }

    @GetMapping("sign")
    public Boolean sign() {
        signService.inputUserInfo();
        return true;
    }



}
