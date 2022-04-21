package com.example.englanguage.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.englanguage.BR;
import com.example.englanguage.LoginActivity;
import com.example.englanguage.model.signup.SignUp;
import com.example.englanguage.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseObservable {
    private String email;
    private String password;
    private String name;
    private String conformPassword;
    private Context context;

    public SignUpViewModel(Context context) {
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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getConformPassword() {
        return conformPassword;
    }

    public void setConformPassword(String conformPassword) {
        this.conformPassword = conformPassword;
        notifyPropertyChanged(BR.conformPassword);
    }

    public void clickSignUp() {

        API.api.postUsers(email, password, name, conformPassword).enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                SignUp signUp = response.body();
                Log.d("signUp", signUp.getMessage());
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(context, "Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
