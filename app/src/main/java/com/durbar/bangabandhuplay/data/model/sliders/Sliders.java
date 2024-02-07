package com.durbar.bangabandhuplay.data.model.sliders;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sliders {
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
    private Data data;
    @Nullable
    @SerializedName("errors")
    @Expose
    private Object errors;

    public Sliders(Boolean status, String message, Data data, Object errors) {
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
