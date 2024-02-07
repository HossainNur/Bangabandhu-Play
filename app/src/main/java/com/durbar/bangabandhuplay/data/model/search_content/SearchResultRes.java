package com.durbar.bangabandhuplay.data.model.search_content;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResultRes {
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
    public List<Data> data;
    @Nullable
    @SerializedName("errors")
    @Expose
    public Object errors;

    public SearchResultRes(Boolean status, String message, List<Data> data, Object errors) {
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
