package com.example;

import com.example.models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tools.ant.taskdefs.optional.PropertyFile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by solg on 13.12.2016.
 */
public class SimpleTestGson {
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



    @Test
    public void getAllUsers() throws IOException {
        String url = "http://localhost:3000/users";


        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        //User[] user = gson.fromJson(responcebody, User[].class);
        //System.out.println(user.toString());

        Type UserlistType= new TypeToken<ArrayList<User>>(){}.getType();

        List<User> userList = gson.fromJson(responcebody, UserlistType);
        assertEquals(userList.size(),11);

        assertEquals(userList.get(0).getPhone(), "1-770-736-8031 x56442");
        assertEquals(userList.get(1).getWebsite(), "anastasia.net");

        Address address = new Address();
        address = userList.get(2).getAddress();
        assertEquals(address.getStreet(),"Douglas Extension");

    }


    @Test
    public void getUsersAdress() throws IOException {
        int userId = 1;
        String url = "http://localhost:3000/users/" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        User user = gson.fromJson(responcebody, User.class);
        Address address = new Address();
        address = user.getAddress();

        assertEquals(address.getCity(),"Gwenborough");
        assertEquals(address.getStreet(),"Kulas Light");
        assertEquals(address.getSuite(),"Apt. 556");
        assertTrue(address.getZipcode()==null, "Zipcode is not empty");

    }
    @Test
    public void getUsersAdressAndGeo() throws IOException {
        int userId = 1;
        String url = "http://localhost:3000/users/" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        User user = gson.fromJson(responcebody, User.class);
        Address address = new Address();
        address = user.getAddress();
        Geo geo= new Geo();
        geo = address.getGeo();

        assertEquals(geo.getLat(),"-37.3159");
        assertEquals(geo.getLng(),"81.1496");

    }

    @Test
    public void getUsersCompany() throws IOException {
        int userId = 1;
        String url = "http://localhost:3000/users/" + userId;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        User user = gson.fromJson(responcebody, User.class);
        Company company = new Company();
        company = user.getCompany();

        assertEquals(company.getName(),"Romaguera-Crona");
        assertEquals(company.getCatchPhrase(),"Multi-layered client-server neural-net");
        assertEquals(company.getBs(),"harness real-time e-markets");

    }
    @Test
    public void getPosts() throws IOException {
        int id = 2;
        String url = "http://localhost:3000/posts/" + id;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        Posts post = gson.fromJson(responcebody, Posts.class);

        assertEquals(post.getUserId().toString(),"1");
       assertEquals(post.getBody(),"est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla");
        assertEquals(post.getTitle(),"qui est esse");

    }
    @Test
    //работа с переносом строки
    public void getComments() throws IOException {
        int id = 1;
        String url = "http://localhost:3000/comments/" + id;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        Comments comments = gson.fromJson(responcebody, Comments.class);

        assertEquals(comments.getPostId().toString(), "1");
        assertEquals(comments.getName(), "id labore ex et quam laborum");
        assertEquals(comments.getEmail(), "Eliseo@gardner.biz");
        assertEquals(comments.getBody(), "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium");
    }
    @Test
    public void getAlbums() throws IOException {
        int id = 2;
        String url = "http://localhost:3000/albums/" + id;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String responcebody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        Albums albums = gson.fromJson(responcebody, Albums.class);

        assertEquals(albums.getUserId().toString(), "1");
        assertEquals(albums.getTitle(), "sunt qui excepturi placeat culpa");

    }

}
