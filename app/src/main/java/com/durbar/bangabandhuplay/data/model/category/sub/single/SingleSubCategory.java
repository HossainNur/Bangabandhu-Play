package com.durbar.bangabandhuplay.data.model.category.sub.single;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleSubCategory {
    @Nullable
    @SerializedName("status")
    @Expose
    private Boolean status;
    @Nullable
    @SerializedName("message")
    @Expose
    private String message;
    @Nullable
    @SerializedName("data")
    @Expose
    private List<Data> data;
    @Nullable
    @SerializedName("errors")
    @Expose
    private Object errors;

    public SingleSubCategory(Boolean status, String message, List<Data> data, Object errors) {
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
