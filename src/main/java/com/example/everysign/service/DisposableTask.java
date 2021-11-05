package com.example.everysign.service;

import com.example.everysign.service.impl.SignService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisposableTask implements DisposableBean {
    @Autowired
    SignService signService;
    @Override
    public void destroy() throws Exception {
        System.out.println("系统退出");
        signService.stop();
    }
}
