package com.example.corey.ignandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by corey on 5/4/17.
 */

public class IGNArticleMetadata {
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("networks")
    @Expose
    private List<String> networks = null;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("subHeadline")
    @Expose
    private String subHeadline;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("articleType")
    @Expose
    private String articleType;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSubHeadline() {
        return subHeadline;
    }

    public void setSubHeadline(String subHeadline) {
        this.subHeadline = subHeadline;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }
}
