package com.example.models;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by solg on 13.12.2016.
 */
public class Posts {

    private Integer userId;

    private Integer id;

    private String title;

    private String body;


    /**
     *
     * @return
     * The userId
     */

    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The userId
     */

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The id
     */

    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */

    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The body
     */

    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */

    public void setBody(String body) {
        this.body = body;
    }

}

