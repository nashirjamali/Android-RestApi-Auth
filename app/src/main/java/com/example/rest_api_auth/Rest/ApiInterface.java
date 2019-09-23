package com.example.rest_api_auth.Rest;

import com.example.rest_api_auth.Model.valueInsert;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<valueInsert> registerUser(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<valueInsert> loginUser(@Field("email") String email,
                                @Field("password") String password);

}

