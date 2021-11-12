package com.example.cherryqrqr.Retrofit.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserSetleHst {

    @SerializedName("userSetleHstSn")
    private Long userSetleHstSn;

    @SerializedName("userSn")
    private Long userSn;

    @SerializedName("mrhstId")
    private String mrhstId;

    @SerializedName("chypayTransferId")
    private String chypayTransferId;

    @SerializedName("setleDt")
    private Date setleDt;

    @SerializedName("setleAmt")
    private Long setleAmt;

    @SerializedName("setleSttusCode")
    private String setleSttusCode;

    @SerializedName("setleErrorCn")
    private String setleErrorCn;

    @SerializedName("mrhstNm")
    private String mrhstNm;

    @SerializedName("mrhstDc")
    private String mrhstDc;

    @SerializedName("userNm")
    private String userNm;

    @SerializedName("cprNm")
    private String cprNm;

    @SerializedName("bizrno")
    private String bizrno;

    @SerializedName("zipAdres")
    private String zipAdres;
}
