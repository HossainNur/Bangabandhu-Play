package com.durbar.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomContentBySlug {
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

    public CustomContentBySlug(Boolean status, String message, Data data, Object errors) {
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


    @Override
    public String toString() {
        return "CustomContentBySlug{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                '}';
    }
}
