package com.example.englanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.englanguage.adapter.ListVocabularyAdapter;

import com.example.englanguage.model.vocabulary.SuccessVocabulary;
import com.example.englanguage.model.vocabulary.Vocabulary;
import com.example.englanguage.viewmodel.VocabularyViewModel;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {

    private Context context = VocabularyActivity.this;
    private RecyclerView recyclerView, recyclerViewSearch;
    private ListVocabularyAdapter adapter;
    private List<SuccessVocabulary> mListVocabulary = new ArrayList<>();
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private EditText edtSearch;
    private ImageView imgSearch;

    private int page = 1;
    private int currentPage = 3;
    private Handler handler = new Handler();
    boolean isLoading = false;
    private Vocabulary vocabulary;
    private VocabularyViewModel vocabularyViewModel;
    private String search = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        layoutManager = new LinearLayoutManager(this);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerViewSearch.setAdapter(adapter);
        adapter = new ListVocabularyAdapter(context);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        imgSearch = findViewById(R.id.imgSearch);
        edtSearch = findViewById(R.id.edt_search);

        vocabularyViewModel = new VocabularyViewModel(mListVocabulary, recyclerView, recyclerViewSearch,
                adapter, progressBar, layoutManager, page, currentPage, handler, isLoading, context, search);

        vocabularyViewModel.clickGetVocabulary(page);
        Toast.makeText(VocabularyActivity.this, "Page 1", Toast.LENGTH_SHORT).show();
        vocabularyViewModel.addEventLoad();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = edtSearch.getText().toString().trim();
                if (search != null) {
                    vocabularyViewModel.clickSearchVocabulary(search);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}