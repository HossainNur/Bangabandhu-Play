package com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content_type_slug")
    @Expose
    private Integer contentTypeSlug;
    @SerializedName("content_type_title")
    @Expose
    private String contentTypeTitle;
    @SerializedName("more_info_slug")
    @Expose
    private String moreInfoSlug;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("frontend_custom_content_section_slider")
    @Expose
    private List<SectionSliders> frontendCustomContentSlider;


    public Data(Integer id, Integer contentTypeSlug, String contentTypeTitle, String moreInfoSlug, Object createdAt, Object updatedAt, List<SectionSliders> frontendCustomContentSlider) {
        this.id = id;
        this.contentTypeSlug = contentTypeSlug;
        this.contentTypeTitle = contentTypeTitle;
        this.moreInfoSlug = moreInfoSlug;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.frontendCustomContentSlider = frontendCustomContentSlider;
    }

    public Integer getId() {
        return id;
    }

    public Integer getContentTypeSlug() {
        return contentTypeSlug;
    }

    public String getContentTypeTitle() {
        return contentTypeTitle;
    }

    public String getMoreInfoSlug() {
        return moreInfoSlug;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public List<SectionSliders> getFrontendCustomContentSlider() {
        return frontendCustomContentSlider;
    }
}
