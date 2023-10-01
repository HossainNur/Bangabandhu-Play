package com.durbar.bangabandhuplay.data.model.sliders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("original")
    @Expose
    private List<Original> original;
    @SerializedName("exception")
    @Expose
    private Object exception;

    public Data(Headers headers, List<Original> original, Object exception) {
        this.headers = headers;
        this.original = original;
        this.exception = exception;
    }

    public Headers getHeaders() {
        return headers;
    }

    public List<Original> getOriginal() {
        return original;
    }

    public Object getException() {
        return exception;
    }
}
