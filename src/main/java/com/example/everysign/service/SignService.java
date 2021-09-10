package com.example.everysign.service;

import com.example.everysign.model.SendTask;
import com.example.everysign.model.User;
import com.example.everysign.model.request.AddUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

@Service
public class SignService {
    Map<String, User> userMap = new ConcurrentHashMap<>();

    BlockingQueue<SendTask> blockingQueue = new DelayQueue<>();

    Thread thread;

    public void init() {
        if (thread == null) {
            thread = new Thread(() -> {
                while (!Thread.interrupted()) {
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
        System.out.println("今日要签到的人员填充");
        init();
        //将用户信息放入队列中
        for (User value : userMap.values()) {
            blockingQueue.offer(SendTask.build(value));
        }
    }

    public void saveUser(AddUserRequest addUserRequest) {
        User orDefault = userMap.getOrDefault(addUserRequest.getUserCode(), new User());
        BeanUtils.copyProperties(addUserRequest, orDefault);
        userMap.put(addUserRequest.getUserCode(), orDefault);
        System.out.println(orDefault);
    }
}
