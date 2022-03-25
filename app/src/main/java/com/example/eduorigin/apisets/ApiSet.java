package com.example.eduorigin.apisets;

import com.example.eduorigin.models.ResponseModelBookLibraryBackup;
import com.example.eduorigin.models.ResponseModelQuiz;
import com.example.eduorigin.models.ResponseModelQuizResult;
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


    @FormUrlEncoded
    @POST("uploadbooksbackup.php")
    Call<ResponseModelBookLibraryBackup> sendBookDataFromAdminBackup(

            @Field("name") String name,
            @Field("description") String description,
            @Field("image") String image,
            @Field("pdf") String pdf

    );


    @GET("read.php")
    Call<List<ResponseModelBookLibraryBackup> >getBookLibraryData();






    @FormUrlEncoded
    @POST("quiz.php")
    Call<ResponseModelQuiz> createQuizFromAdmin(

            @Field("title") String title,
            @Field("question") String question,
            @Field("option_1") String option_1,
            @Field("option_2") String option_2,
            @Field("option_3") String option_3,
            @Field("option_4") String option_4,
            @Field("correct_answer") String correct_answer


    );


    @GET("readquiz.php")
    Call<List<ResponseModelQuiz> >getQuizData();




    @FormUrlEncoded
    @POST("quizresultsubmit.php")
    Call<ResponseModelQuizResult> submitQuizResult(

            @Field("email") String email,
            @Field("result") Integer result,
            @Field("verdict") String verdict

    );

    @FormUrlEncoded
    @POST("getonlyresult.php")
    Call<List<ResponseModelQuizResult>> getOnlyResultFromDB(

            @Field("email") String email
    );










}
