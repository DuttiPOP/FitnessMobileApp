package com.fitnessapp.api;



import android.content.Context;

import com.fitnessapp.R;
import com.fitnessapp.auth.AuthRequestBody;
import com.fitnessapp.user.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private Api api;

    private final String authorization;
    private final String api_server;
    private final String registration;



    public ApiService(Context context){

        api_server = context.getString(R.string.api_server);
        authorization = context.getString(R.string.authorization);
        registration = context.getString(R.string.registration);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api_server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

    }

    public Call<User> loginUser(AuthRequestBody authRequestBody){
        return api.loginUser(authRequestBody, authorization);
    }

}
