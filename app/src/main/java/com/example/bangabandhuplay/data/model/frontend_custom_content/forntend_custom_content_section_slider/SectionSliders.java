package com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionSliders {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("frontend_custom_content_type_id")
    @Expose
    private Integer frontendCustomContentTypeId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("content_url")
    @Expose
    private String contentUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order")
    @Expose
    private Integer order;


    public SectionSliders(Integer id, String title, Integer frontendCustomContentTypeId, String image, String contentUrl, String status, Integer order) {
        this.id = id;
        this.title = title;
        this.frontendCustomContentTypeId = frontendCustomContentTypeId;
        this.image = image;
        this.contentUrl = contentUrl;
        this.status = status;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getFrontendCustomContentTypeId() {
        return frontendCustomContentTypeId;
    }

    public String getImage() {
        return image;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getStatus() {
        return status;
    }

    public Integer getOrder() {
        return order;
    }
}
