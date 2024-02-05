package com.durbar.bangabandhuplay.data.model.ott_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CastAndCrew {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("about")
    @Expose
    public String about;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("nationality")
    @Expose
    public String nationality;
    @SerializedName("upcomming")
    @Expose
    public String upcomming;
    @SerializedName("previous")
    @Expose
    public String previous;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("pivot")
    @Expose
    public Pivot pivot;

    public CastAndCrew(Integer id, String name, String about, String dob, String image, String nationality, String upcomming, String previous, String createdAt, String updatedAt, Pivot pivot) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.dob = dob;
        this.image = image;
        this.nationality = nationality;
        this.upcomming = upcomming;
        this.previous = previous;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pivot = pivot;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getDob() {
        return dob;
    }

    public String getImage() {
        return image;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUpcomming() {
        return upcomming;
    }

    public String getPrevious() {
        return previous;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Pivot getPivot() {
        return pivot;
    }
}
