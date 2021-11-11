package com.example.cherryqrqr.Retrofit;

import com.example.cherryqrqr.Retrofit.Entities.JoinDTO;
import com.example.cherryqrqr.Retrofit.Entities.UserCheckDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("userCheck")
    Call<UserCheckDTO> getCode(
            @Header("Authorization") String idToken
    );

//    @POST("join")
//    Call<JoinDTO> getCode(
//            @Query("uid") String uid,
//            @Query("token") String idToken
//    );

    @POST("join")
    @FormUrlEncoded
    Call<JoinDTO> getCode(
        @Field("uid") String uid,
        @Field("token") String idToken
    );
}
