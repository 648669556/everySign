package com.example.everysign.model;

import com.example.everysign.util.HttpClientHelper;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 发送http请求的任务
 */
@Data
public class SendTask implements Runnable, Delayed {

    public static SendTask build(User userInfo) {
        SendTask sendTask = new SendTask();
        sendTask.setUserInfo(userInfo);
        return sendTask;
    }

    User userInfo;

    @Override
    public void run() {
        try {
            HttpClientHelper.doPostTask(this);
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS));
    }
}
