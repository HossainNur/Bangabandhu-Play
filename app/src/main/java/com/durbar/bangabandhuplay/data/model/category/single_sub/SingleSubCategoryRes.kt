package com.durbar.bangabandhuplay.data.model.category.single_sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleSubCategoryRes {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("errors")
    @Expose
    public Object errors;

    public SingleSubCategoryRes(Boolean status, String message, Data data, Object errors) {
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
