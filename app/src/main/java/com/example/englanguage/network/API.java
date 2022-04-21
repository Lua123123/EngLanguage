package com.example.englanguage.network;

import com.example.englanguage.model.login.Login;
import com.example.englanguage.model.signup.SignUp;
import com.example.englanguage.model.topic.Topic;
import com.example.englanguage.model.login.UserLogin;
import com.example.englanguage.model.vocabulary.Vocabulary;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    public static final String DOMAIN = "http://192.168.11.94/quanlytuvung/public/api/";
    API api = new Retrofit.Builder()
            .baseUrl(DOMAIN) // API base url
            .addConverterFactory(GsonConverterFactory.create()) // Factory phụ thuộc vào format JSON trả về
            .build()
            .create(API.class);

    @FormUrlEncoded
    @POST("auth/signup")
    Call<SignUp> postUsers(@Field("email") String email, @Field("password") String password,
                           @Field("name") String name, @Field("password_confirmation") String password_confirmation);

    @POST("auth/login")
    Call<Login> getUsers(@Body UserLogin userLogin);

    @FormUrlEncoded
    @POST("topic/getAllTopic")
    Call<Topic> getTopics(@Field("user_create") int user_create);

    @FormUrlEncoded
    @POST("vocabulary/getVocabulary")
    Call<Vocabulary> getVocabulary(@Field("user_create") int user_create, @Field("search") String search);

//    @POST("auth/login")
//    Call<Login> getUsers(@Body String email, @Body String password,
//                         @Body Boolean remember_me);


//    @Headers("Cache-Control: max-age=640000")
//    @POST("auth/login")
//    Call<Login> getUsers(@Header("email") String email, @Header("password") String password,
//                         @Header("remember_me") Boolean remember_me);


//    @FormUrlEncoded
//    @POST("auth/login")
//    Call<Login> getUsers(@Field("email") String email, @Field("password") String password,
//                         @Field("remember_me")  Boolean remember_me);

}
