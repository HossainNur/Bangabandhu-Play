package com.durbar.bangabandhuplay.data.model.category.root.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleRootCategory {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data ;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public SingleRootCategory(Boolean status, String message, List<Data> data, Object errors) {
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
