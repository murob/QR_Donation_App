package com.example.cherryqrqr.Retrofit;

import com.example.cherryqrqr.Retrofit.Entities.DonateDTO;
import com.example.cherryqrqr.Retrofit.Entities.JoinDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @GET("userCheck")
    Call<JoinDTO> getCode(
            @Header("Authorization") String idToken
    );

    @POST("withdrawal")
    Call<JoinDTO> getPost(
            @Header("Authorization") String idToken
    );

    @POST("join")
    @FormUrlEncoded
    Call<JoinDTO> getCode(
        @Field("uid") String uid,
        @Field("token") String idToken
    );

    @POST("donate")
    @FormUrlEncoded
    Call<DonateDTO> getCode(
            @Header("Authorization") String idToken,
            @Field("mrhstId") String mrhstId,
            @Field("setleAmt") String setleAmt
    );
}
