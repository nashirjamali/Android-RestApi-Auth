package com.example.rest_api_auth.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class valueInsert {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
