package com.example.corey.ignandroid.models;

/**
 * Created by corey on 5/3/17.
 */

public class IGNObject {
    public String date;
    public String title;
    public String type;
    public String imageUrl;
    public String url;

    public IGNObject(String date, String title, String type, String imageUrl, String url) {
        this.date = date;
        this.title = title;
        this.type = type;
        this.imageUrl = imageUrl;
        this.url = url;
    }
}
