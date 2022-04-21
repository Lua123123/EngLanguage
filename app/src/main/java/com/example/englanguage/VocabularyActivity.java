package com.example.englanguage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.englanguage.adapter.ListVocabularyAdapter;

import com.example.englanguage.model.vocabulary.SuccessVocabulary;
import com.example.englanguage.model.vocabulary.Vocabulary;
import com.example.englanguage.network.API;
import com.example.englanguage.viewmodel.SignUpViewModel;
import com.example.englanguage.viewmodel.VocabularyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabularyActivity extends AppCompatActivity {

    private Context context = VocabularyActivity.this;
    private RecyclerView recyclerView;
    private ListVocabularyAdapter adapter;
    private List<SuccessVocabulary> mListVocabulary = new ArrayList<>();
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;

    private int page = 1;
    private int currentPage = 3;
    private Handler handler = new Handler();
    boolean isLoading = false;
    VocabularyViewModel vocabularyViewModel;

    public VocabularyViewModel getVocabularyViewModel() {
        return vocabularyViewModel;
    }

    public void setVocabularyViewModel(VocabularyViewModel vocabularyViewModel) {
        this.vocabularyViewModel = vocabularyViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ListVocabularyAdapter(mListVocabulary, context);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        vocabularyViewModel = new VocabularyViewModel(mListVocabulary, recyclerView,
                adapter, progressBar, layoutManager, page, currentPage, handler, isLoading, context);

        vocabularyViewModel.clickGetVocabulary(page);
        Toast.makeText(VocabularyActivity.this, "Page 1", Toast.LENGTH_SHORT).show();
        vocabularyViewModel.addEventLoad();

        //livedata
//        vocabularyViewModel.getListVocabularyLiveData().observe(this, new Observer<List<SuccessVocabulary>>() {
//            @Override
//            public void onChanged(List<SuccessVocabulary> successVocabularies) {
//                adapter = new ListVocabularyAdapter(successVocabularies);
//                recyclerView.setAdapter(adapter);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}