package com.example.englanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.englanguage.databinding.ActivitySignUpBinding;
import com.example.englanguage.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    Context context = SignUpActivity.this;
    TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActivitySignUpBinding activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        SignUpViewModel signUpViewModel = new SignUpViewModel(context);
        activitySignUpBinding.setSignUpViewModel(signUpViewModel);

        tv_next = findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



//        SignUpModel signUpModel = new SignUpModel("hvna28888@gmail.com", "12345678", "na", "12345678");
//        livedata.mSignUp.postValue(signUpModel);

//        livedata.mSignUp.observe(this, new Observer<SignUpModel>() {
//            @Override
//            public void onChanged(SignUpModel signUpModel) {
//                Log.d("iii", livedata.mSignUp.getValue().getName());
//            }
//        });


//        String name = signUpViewModel.getName();
//        String password = signUpViewModel.getPassword();
//        String conformPassword = signUpViewModel.getConformPassword();
//        String email = signUpViewModel.getEmail();
//        signUpViewModel.clickSignUp(email, password, name, conformPassword);

    }
}