package com.example;

import com.example.models.User;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * Created by solg on 07.12.2016.
 */
public class SimpleHttp {
    private CloseableHttpClient httpClient;

    @BeforeClass
    public void setUp(){
        httpClient= HttpClients.createDefault();
    }
    @AfterClass
    public void tearDown() throws IOException {
        httpClient.close();
    }
    @Test
    public void getUserById() throws IOException {
        int userId = 1;
        String url = "http://localhost:3000/users/" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        assertEquals(code, 200, "Users list are empty!");

    }

    @Test
    public void getComment() throws IOException {
        String url = "http://localhost:3000/comments";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertEquals(code, 200, "Wrong response");
    }
    @Test
    public void getCommentById() throws IOException {
        int id=2;
        String url = "http://localhost:3000/comments/" + id;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertEquals(code, 200, "Wrong response");
    }

    @Test
    public void getUserByIdWithModel() throws IOException {
        int userId = 1;
        String url = "http://localhost:3000/users/" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        User user = gson.fromJson(responcebody, User.class);

        assertEquals(user.getName(),"Leanne Graham");
        assertEquals(user.getUsername(),"Bret");
        assertEquals(user.getEmail(),"Sincere@april.biz");
    }
}
