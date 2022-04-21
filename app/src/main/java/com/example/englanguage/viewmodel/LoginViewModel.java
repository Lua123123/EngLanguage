package com.example.englanguage.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.englanguage.BR;
import com.example.englanguage.TopicActivity;
import com.example.englanguage.model.login.Login;
import com.example.englanguage.model.login.UserLogin;
import com.example.englanguage.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;
    private Boolean remember_me = true;
    private Context context;

    public LoginViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }


    public void clickLogin() {
        UserLogin userLogin = new UserLogin(getEmail(), getPassword(), remember_me);
        Log.d("userLogin", userLogin.toString());
        API.api.getUsers(userLogin).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login login = response.body();
                Log.d("login", login.toString()); //.getStatus()
                Intent intent = new Intent(context, TopicActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(context, "Call api failed", Toast.LENGTH_SHORT).show();
                Log.d("login", "failed");
            }
        });
    }
}
