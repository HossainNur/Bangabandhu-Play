package com.example.bangabandhuplay.data.model.frontend_custom_content.forntend_custom_content_section_slider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FrontendCustomContentSlider {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public FrontendCustomContentSlider(Boolean status, String message, Data data, Object errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }


    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public Object getErrors() {
        return errors;
    }
}
