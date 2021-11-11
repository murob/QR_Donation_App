package com.example.cherryqrqr.Retrofit.Entities;

import com.google.gson.annotations.SerializedName;

public class UserCheckDTO {

    @SerializedName("data")
    public T data;

    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;


    @Override
    public String toString() {
        return "UserCheckDTO{" +
                "code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
