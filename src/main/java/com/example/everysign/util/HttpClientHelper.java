package com.example.everysign.util;

import com.example.everysign.model.SendTask;
import com.example.everysign.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HttpClientHelper {
    static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    static String targetUrl = "http://ims.aiit.edu.cn/signMobile/saveSign.do";

    public static void doPostTask(SendTask task) throws IOException {
        HttpPost httpPost = buildPost(task.getUserInfo());
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        System.out.printf("[time]%n%s%n [user]%n%s%n [response]%n%s%n", LocalDateTime.now(), task.getUserInfo().getUserName(), show(entity));
    }

    public static String show(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity, "utf-8");
    }

    public static List<BasicNameValuePair> boxUserInfo(User user) {
        List<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
        basicNameValuePairs.add(new BasicNameValuePair("_userCode", user.getUserCode()));
        basicNameValuePairs.add(new BasicNameValuePair("signAddress", user.getSignAddress()));
        basicNameValuePairs.add(new BasicNameValuePair("_userName", user.getUserName()));
        basicNameValuePairs.add(new BasicNameValuePair("signAddressName", user.getSignAddressName()));
        basicNameValuePairs.add(new BasicNameValuePair("_userType", "1"));
        basicNameValuePairs.add(new BasicNameValuePair("appId", "1111111111111111111111111111111"));
        basicNameValuePairs.add(new BasicNameValuePair("userId", user.getUserId()));
        return basicNameValuePairs;
    }

    /**
     * 构造一个post请求
     *
     * @param user
     * @return
     */
    public static HttpPost buildPost(User user) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(targetUrl);
        HttpEntity entity = new UrlEncodedFormEntity(boxUserInfo(user));
        post.setEntity(entity);
        return post;
    }

}
