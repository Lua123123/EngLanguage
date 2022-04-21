package com.example.englanguage.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englanguage.adapter.ListVocabularyAdapter;
import com.example.englanguage.fragmentflipcard1.FlipCardFragment1;
import com.example.englanguage.model.vocabulary.SuccessVocabulary;
import com.example.englanguage.model.vocabulary.Vocabulary;
import com.example.englanguage.network.API;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabularyViewModel extends ViewModel {
    private MutableLiveData<List<SuccessVocabulary>> mListVocabularyLiveData;
    private List<SuccessVocabulary> mListVocabulary = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListVocabularyAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private int page = 1;
    private int currentPage = 3;
    Handler handler = new Handler();
    boolean isLoading = false;
    private Context context;
    private Vocabulary vocabulary;
    private String WORD;

    public VocabularyViewModel(List<SuccessVocabulary> mListVocabulary, RecyclerView recyclerView, ListVocabularyAdapter adapter, ProgressBar progressBar, LinearLayoutManager layoutManager, int page, int currentPage, Handler handler, boolean isLoading, Context context, @NonNull Closeable... closeables) {
        super(closeables);

//MutableLiveData<List<SuccessVocabulary>> mListVocabularyLiveData,
//        this.mListVocabularyLiveData = mListVocabularyLiveData;
        this.mListVocabulary = mListVocabulary;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.progressBar = progressBar;
        this.layoutManager = layoutManager;
        this.page = page;
        this.currentPage = currentPage;
        this.handler = handler;
        this.isLoading = isLoading;
        this.context = context;
    }

    public VocabularyViewModel() {
        mListVocabularyLiveData = new MutableLiveData<>();
        clickGetVocabulary(page);
        addEventLoad();
    }

    public MutableLiveData<List<SuccessVocabulary>> getListVocabularyLiveData() {
        return mListVocabularyLiveData;
    }

    public void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == mListVocabulary.size() - 1) {
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mListVocabulary.add(null);
                adapter.notifyItemInserted(mListVocabulary.size() - 1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListVocabulary.remove(mListVocabulary.size() - 1);
                adapter.notifyItemRemoved(mListVocabulary.size());
                page = page + 1;
                clickGetVocabulary(page);
                adapter.notifyDataSetChanged();
                isLoading = false;
                if (page == currentPage) {
                    return;
                }
            }
        }, 2000);
    }

    public List<SuccessVocabulary> clickGetVocabulary(int page) {
        API.api.getVocabulary(1, "").enqueue(new Callback<Vocabulary>() {
            @Override
            public void onResponse(Call<Vocabulary> call, Response<Vocabulary> response) {
                vocabulary = response.body();
                Toast.makeText(context, "Page " + page, Toast.LENGTH_SHORT).show();
                if (adapter == null) {
                    getListVocabulary(vocabulary);
                } else if (page <= currentPage) {
                    Log.d("iiiiiii", String.valueOf(page));
                    if (page == 1) {
                        getListVocabularyLoadMore1(vocabulary);
                        Toast.makeText(context, "Page " + page, Toast.LENGTH_SHORT).show();
                    }
                    else if (page == 2) {
                        getListVocabularyLoadMore2(vocabulary);
                        Toast.makeText(context, "Page " + page, Toast.LENGTH_SHORT).show();
                    }
                    else if (page == 3) {
                        getListVocabularyLoadMore3(vocabulary);
                    }
                } else if (page > currentPage) {
                    Toast.makeText(context, "Page " + currentPage + " HẾT", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("iiiiiiiiiii", vocabulary.getSuccess().get(1).getWord());
            }

            @Override
            public void onFailure(Call<Vocabulary> call, Throwable t) {
                Toast.makeText(context, "Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    private List<SuccessVocabulary> getListVocabulary(Vocabulary vocabulary) {
        for (int i = 0; i < vocabulary.getSuccess().size(); i++) {
            SuccessVocabulary successVocabulary = new SuccessVocabulary(vocabulary.getSuccess().get(i).getWord(),
                    vocabulary.getSuccess().get(i).getMean(), vocabulary.getSuccess().get(i).getExample());
            mListVocabulary.add(successVocabulary);
            adapter.setData(mListVocabulary);
        }
        return null;
    }

    private List<SuccessVocabulary> getListVocabularyLoadMore1(Vocabulary vocabulary) {
        int vitri = mListVocabulary.size() - 1;
        int soluongadd = vocabulary.getSuccess().size();
        for (int i = 0; i < 5; i++) {
            SuccessVocabulary successVocabulary = new SuccessVocabulary(vocabulary.getSuccess().get(i).getWord(),
                    vocabulary.getSuccess().get(i).getMean(), vocabulary.getSuccess().get(i).getExample());
            mListVocabulary.add(successVocabulary);
            adapter.setData(mListVocabulary);
        }
        adapter.notifyItemRangeInserted(vitri, soluongadd);
        return null;
    }

    private List<SuccessVocabulary> getListVocabularyLoadMore2(Vocabulary vocabulary) {
        int vitri = mListVocabulary.size() - 1;
        int soluongadd = vocabulary.getSuccess().size() - 1;

        for (int i = 6; i < 10; i++) {
            SuccessVocabulary successVocabulary = new SuccessVocabulary(vocabulary.getSuccess().get(i).getWord(),
                    vocabulary.getSuccess().get(i).getMean(), vocabulary.getSuccess().get(i).getExample());
            mListVocabulary.add(successVocabulary);
            adapter.setData(mListVocabulary);
        }
        adapter.notifyItemRangeInserted(vitri, soluongadd);
        return null;
    }

    private List<SuccessVocabulary> getListVocabularyLoadMore3(Vocabulary vocabulary) {
        int vitri = mListVocabulary.size() - 1;
        int soluongadd = vocabulary.getSuccess().size() - 1;

        for (int i = 11; i < soluongadd; i++) {
            SuccessVocabulary successVocabulary = new SuccessVocabulary(vocabulary.getSuccess().get(i).getWord(),
                    vocabulary.getSuccess().get(i).getMean(), vocabulary.getSuccess().get(i).getExample());
            mListVocabulary.add(successVocabulary);
            adapter.setData(mListVocabulary);
        }
        adapter.notifyItemRangeInserted(vitri, soluongadd);
        return null;
    }
}
