package com.example.englanguage;

import androidx.lifecycle.MutableLiveData;

import com.example.englanguage.viewmodel.SignUpModel;

abstract class livedata {
    static MutableLiveData<SignUpModel> mSignUp = new MutableLiveData<>();
}
