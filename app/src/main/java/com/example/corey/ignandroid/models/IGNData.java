package com.example.corey.ignandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by corey on 5/3/17.
 */

public class IGNData {
    @SerializedName("thumbnails")
    @Expose
    private List<IGNThumbnail> thumbnails = null;
    @SerializedName("metadata")
    @Expose
    private IGNVideoMetadata metadata;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public List<IGNThumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<IGNThumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public IGNVideoMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(IGNVideoMetadata metadata) {
        this.metadata = metadata;
    }
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
