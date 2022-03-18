package com.example.eduorigin.apisets;

import com.example.eduorigin.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSet {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModel> userVerification(
            @Field("email") String email,
            @Field("password") String password

    );
}
