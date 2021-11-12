package com.example.cherryqrqr.Retrofit.Entities;

import com.google.gson.annotations.SerializedName;

public class JoinDTO {

    @SerializedName("data")
    public User data;

    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;


    @Override
    public String toString() {
        return "JoinDTO{" +
                "data='" + data + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
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
}
