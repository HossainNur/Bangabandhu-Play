package com.example.bangabandhuplay.data.model.category.sub_sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubSubCategory {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Data> data ;
    @SerializedName("errors")
    @Expose
    public Object errors;

    public SubSubCategory(Boolean status, String message, List<Data> data, Object errors) {
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
