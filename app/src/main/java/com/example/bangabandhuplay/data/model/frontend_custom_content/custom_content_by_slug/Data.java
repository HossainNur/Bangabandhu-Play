package com.example.bangabandhuplay.data.model.frontend_custom_content.custom_content_by_slug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("headers")
    @Expose
    public Headers headers;
    @SerializedName("original")
    @Expose
    public List<Original> original;
    @SerializedName("exception")
    @Expose
    public Object exception;

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
