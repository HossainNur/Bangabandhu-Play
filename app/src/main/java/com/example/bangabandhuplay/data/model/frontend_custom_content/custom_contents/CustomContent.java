package com.example.bangabandhuplay.data.model.frontend_custom_content.custom_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomContent {
    @SerializedName("status")
    @Expose
    private final Boolean status;
    @SerializedName("message")
    @Expose
    private final String message;
    @SerializedName("data")
    @Expose
    private final List<Data> data;
    @SerializedName("errors")
    @Expose
    private final Object errors;


    public CustomContent(Boolean status, String message, List<Data> data, Object errors) {
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

    public List<Data> getData() {
        return data;
    }

    public Object getErrors() {
        return errors;
    }
}
