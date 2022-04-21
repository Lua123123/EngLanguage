package com.example.englanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.englanguage.databinding.ActivityLoginBinding;
import com.example.englanguage.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    Context context = LoginActivity.this;
    TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = new LoginViewModel(context);
        activityLoginBinding.setLoginViewModel(loginViewModel);

        tv_next = findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, TopicActivity.class);
                startActivity(intent);
            }
        });

//        SignUpModel signUpModel = new SignUpModel("hvna2889988@gmail.com", "12345678", "na22", "12345678");
//        livedata.mSignUp.postValue(signUpModel);

    }
}