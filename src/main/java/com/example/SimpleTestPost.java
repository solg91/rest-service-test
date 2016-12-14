package com.example;

import com.example.models.Address;
import com.example.models.Company;
import com.example.models.Geo;
import com.example.models.User;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by solg on 13.12.2016.
 */
public class SimpleTestPost {
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
    public void getPosts() throws IOException {
        String url = "http://localhost:3000/users";
        HttpPost httpPost = new HttpPost(url);
        List<User> userList = new ArrayList<User>();

        User newUser = new User();
         newUser.setId(14);
         newUser.setName("NewUser");
         newUser.setUsername("Olga");

        Address adress = new Address();
        adress.setCity("blabla");
        adress.setStreet("mystreet");
        adress.setSuite("Apt. 556");
        adress.setZipcode("2323525");

        Geo geo = new Geo();
        geo.setLat("-37.3159");
        geo.setLng("81.1496");
        adress.setGeo(geo);
        newUser.setAddress(adress);
        newUser.setPhone("0962323236");
        newUser.setWebsite("blabla.org");

        Company company = new Company();
        company.setName("mycompany");
        company.setCatchPhrase("Phrase");
        company.setBs("harness real-time e-markets");

        newUser.setCompany(company);

        Gson gson = new Gson();
        String post = gson.toJson(newUser);
        System.out.println(post);
        HttpEntity httpEntity =  new StringEntity(post);


        httpPost.setEntity(httpEntity);
//        userList.add(newUser);
//
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("id", "13"));
//        urlParameters.add(new BasicNameValuePair("username", "Bret"));
//        urlParameters.add(new BasicNameValuePair("email", "myemail"));


       // httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
        CloseableHttpResponse response = httpClient.execute(httpPost);
    }

}
