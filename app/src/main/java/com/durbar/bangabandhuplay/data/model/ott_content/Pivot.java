package com.durbar.bangabandhuplay.data.model.ott_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {
    @SerializedName("ott_content_id")
    @Expose
    public Integer ottContentId;
    @SerializedName("cast_and_crew_id")
    @Expose
    public Integer castAndCrewId;
    @SerializedName("role")
    @Expose
    public String role;

    public Pivot(Integer ottContentId, Integer castAndCrewId, String role) {
        this.ottContentId = ottContentId;
        this.castAndCrewId = castAndCrewId;
        this.role = role;
    }

    public Integer getOttContentId() {
        return ottContentId;
    }

    public Integer getCastAndCrewId() {
        return castAndCrewId;
    }

    public String getRole() {
        return role;
    }
}
