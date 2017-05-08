package com.example.corey.ignandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by corey on 5/4/17.
 */

public class IGNArticlesResponse {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("data")
    @Expose
    private List<IGNData> data = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<IGNData> getData() {
        return data;
    }

    public void setData(List<IGNData> data) {
        this.data = data;
    }
}
