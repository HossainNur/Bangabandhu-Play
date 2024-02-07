package com.durbar.bangabandhuplay.data.model.ott_content;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleOttContent {
    @Nullable
    @SerializedName("status")
    @Expose
    public Boolean status;
    @Nullable
    @SerializedName("message")
    @Expose
    public String message;
    @Nullable
    @SerializedName("data")
    @Expose
    public Data data;
    @Nullable
    @SerializedName("errors")
    @Expose
    public Object errors;

    public SingleOttContent(Boolean status, String message, Data data, Object errors) {
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
