package com.gliesereum.advisorapp.network.json.artbond;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ImagesItem {

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("url")
    private String url;

    @SerializedName("blockMediaType")
    private String blockMediaType;

    @SerializedName("fileId")
    private Object fileId;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setBlockMediaType(String blockMediaType) {
        this.blockMediaType = blockMediaType;
    }

    public String getBlockMediaType() {
        return blockMediaType;
    }

    public void setFileId(Object fileId) {
        this.fileId = fileId;
    }

    public Object getFileId() {
        return fileId;
    }
}