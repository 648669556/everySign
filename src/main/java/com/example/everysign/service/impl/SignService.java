package com.example.everysign.service.impl;

import com.example.everysign.model.SendTask;
import com.example.everysign.model.User;
import com.example.everysign.model.UserInfo;
import com.example.everysign.model.request.AddUserRequest;
import com.example.everysign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
@Validated
public class SignService {
    @Autowired
    UserService userService;
    BlockingQueue<SendTask> blockingQueue = new DelayQueue<>();

    Thread thread;

    public void init() {
        if (thread == null) {
            thread = new Thread(() -> {
                while (!Thread.interrupted()) {
                    // 防止无可运行任务时cpu出现盲等
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SendTask peek = blockingQueue.peek();
                    if (peek == null || peek.getDelay(TimeUnit.SECONDS) > 0) {
                        continue;
                    } else {
                        blockingQueue.poll();
                    }
                    peek.run();
                }
            });
            thread.start();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void inputUserInfo() {
        log.info("今日要签到的人员填充");
        init();
        // 清空昨天重试失败遗留的任务
        if (blockingQueue != null && blockingQueue.size() > 0) {
            blockingQueue.clear();
        }
        List<UserInfo> userInfos = userService.listUser();

        //将用户信息放入队列中
        for (UserInfo userInfo : userInfos) {
            blockingQueue.offer(SendTask.build(User.Convert(userInfo), blockingQueue));
        }
    }

    public boolean saveUser(AddUserRequest addUserRequest) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(addUserRequest, userInfo);
        Boolean result = userService.saveUser(userInfo);
        if (result) {
            log.info("用户信息维护：\n {}", userInfo);
        } else {
            log.info("维护结果：{}", false);
        }
        return result;
    }
}
