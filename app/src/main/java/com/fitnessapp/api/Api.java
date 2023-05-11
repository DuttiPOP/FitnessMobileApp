package com.fitnessapp.api;

import com.fitnessapp.auth.AuthRequestBody;
import com.fitnessapp.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
    @POST
    Call<User> loginUser(@Body AuthRequestBody userLogin, @Url String authorization);
}
