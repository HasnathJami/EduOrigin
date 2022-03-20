package com.example.eduorigin.apisets;

import com.example.eduorigin.models.ResponseModelBookLibrary;
import com.example.eduorigin.models.ResponseModelRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModelRegistration> userVerification(
            @Field("email") String email,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseModelRegistration> sendRegistrationData(

            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password

    );


    @GET("read.php")
    Call<List<ResponseModelBookLibrary> >getBookLibraryData();
}
