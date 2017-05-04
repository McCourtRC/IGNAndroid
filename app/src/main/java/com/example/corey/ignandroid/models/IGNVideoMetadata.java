package com.example.corey.ignandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by corey on 5/3/17.
 */

public class IGNVideoMetadata {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("networks")
    @Expose
    private List<String> networks = null;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("longTitle")
    @Expose
    private String longTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }
}
