package com.example.everysign.model;

import com.example.everysign.util.HttpClientHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 发送http请求的任务
 */
@Data
@Slf4j
public class SendTask implements Runnable, Delayed {

    private final Long retryTime = 60L;
    public static SendTask build(User userInfo, BlockingQueue blockingQueue) {
        SendTask sendTask = new SendTask();
        sendTask.setUserInfo(userInfo);
        sendTask.setBlockingQueue(blockingQueue);
        return sendTask;
    }

    User userInfo;
    BlockingQueue<SendTask> blockingQueue;

    @Override
    public void run() {
        try {
            HttpClientHelper.doPostTask(this);
        } catch (IOException e) {
            log.error("sign failed, user: {}, cause: {}", userInfo, e);
            retry();
            log.info("task will be retry at {} minuets later", retryTime);
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime signTime = userInfo.getSignTime();
        return getTime(signTime) - getTime(now);
    }

    private long getTime(LocalDateTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        return (hour * 10000 + minute * 100 + second);
    }

    private void retry(){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setSignTime(LocalDateTime.now().plusMinutes(retryTime));
        blockingQueue.offer(SendTask.build(user, blockingQueue));
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS));
    }
}
