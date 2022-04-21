package com.example.englanguage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englanguage.FlipCardActivity1;
import com.example.englanguage.R;
import com.example.englanguage.model.vocabulary.SuccessVocabulary;
import com.example.englanguage.viewmodel.VocabularyViewModel;

import java.io.Serializable;
import java.util.List;

public class ListVocabularyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SuccessVocabulary> mListVocabulary;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;

    public void setData(List<SuccessVocabulary> mListVocabulary) {
        this.mListVocabulary = mListVocabulary;
        notifyDataSetChanged();
    }

    public ListVocabularyAdapter(List<SuccessVocabulary> mListVocabulary, Context context) {
        this.mListVocabulary = mListVocabulary;
        this.context = context;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_vocabulary_item, parent, false);
            return new ListVocabularyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_load_more, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final SuccessVocabulary successVocabulary = mListVocabulary.get(position);
        if (holder instanceof ListVocabularyViewHolder) {
            ListVocabularyViewHolder listVocabularyViewHolder = (ListVocabularyViewHolder) holder;
            if (successVocabulary == null) {
                return;
            }
            listVocabularyViewHolder.tvWord.setText(successVocabulary.getWord());
            listVocabularyViewHolder.tvMean.setText(successVocabulary.getMean());
            listVocabularyViewHolder.tvExample.setText(successVocabulary.getExample());
            if (listVocabularyViewHolder != null) {
                listVocabularyViewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickToDetail(successVocabulary);//
                    }
                });
            }
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    private void onClickToDetail(SuccessVocabulary successVocabulary) { //
//        Intent intent = new Intent(context, FlipCardActivity1.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object", successVocabulary);
//        intent.putExtras(bundle);
//        context.startActivity(intent);

        Intent intent2 = new Intent(context, FlipCardActivity1.class);
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("object", (Serializable) mListVocabulary);
        intent2.putExtras(bundle2);
        context.startActivity(intent2);
    }

    @Override
    public int getItemViewType(int position) {
        return mListVocabulary.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ListVocabularyViewHolder holder, int position) {
//        SuccessVocabulary successVocabulary = mListVocabulary.get(position);
//
//        if (successVocabulary == null) {
//            return;
//        }
//        holder.tvWord.setText(successVocabulary.getWord());
//        holder.tvMean.setText(successVocabulary.getMean());
//        holder.tvExample.setText(successVocabulary.getExample());
////        holder.layout_item.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                onClickToDetail(successVocabulary);
////            }
////        });
//    }

    @Override
    public int getItemCount() {
        if (mListVocabulary != null) {
            return mListVocabulary.size();
        }
        return 0;
    }

    public class ListVocabularyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;
        TextView tvMean;
        TextView tvExample;
        LinearLayout layout_item;

        public ListVocabularyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.word);
            tvMean = itemView.findViewById(R.id.mean);
            tvExample = itemView.findViewById(R.id.example);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }


    //    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_vocabulary_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        SuccessVocabulary successVocabulary = mListVocabulary.get(position);
//        if (successVocabulary == null) {
//            Log.d("vocabulary5555", String.valueOf(successVocabulary));
//            return;
//        }
//        holder.tvWord.setText(successVocabulary.getWord());
//        holder.tvMean.setText(successVocabulary.getMean());
//        holder.tvExample.setText(successVocabulary.getExample());
//        Log.d("vocabulary6666", String.valueOf(successVocabulary));
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mListVocabulary != null) {
//            return mListVocabulary.size();
//        }
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvWord;
//        TextView tvMean;
//        TextView tvExample;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvWord = itemView.findViewById(R.id.word);
//            tvMean = itemView.findViewById(R.id.mean);
//            tvExample = itemView.findViewById(R.id.example);
//        }
//    }
    public void release() {
        context = null;
    }

}
