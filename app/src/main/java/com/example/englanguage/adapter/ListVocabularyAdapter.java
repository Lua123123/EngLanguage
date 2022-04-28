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

import com.example.englanguage.FlipCardActivity;
import com.example.englanguage.OneVocabularyActivity;
import com.example.englanguage.R;
import com.example.englanguage.VocabularyOfTopicActivity;
import com.example.englanguage.model.vocabulary.SuccessVocabulary;

import java.io.Serializable;
import java.util.List;

public class ListVocabularyAdapter extends RecyclerView.Adapter<ListVocabularyAdapter.ViewHolder> {
    private List<SuccessVocabulary> mListVocabulary;
    private Context context;

    public void setData(List<SuccessVocabulary> mListVocabulary) {
        this.mListVocabulary = mListVocabulary;
        notifyDataSetChanged();

    }

    public ListVocabularyAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_vocabulary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SuccessVocabulary successVocabulary = mListVocabulary.get(position);
        if (successVocabulary == null) {
            return;
        }
        if (successVocabulary != null || mListVocabulary != null ) {
            holder.tvWord.setText(mListVocabulary.get(position).getWord());
            holder.tvMean.setText(mListVocabulary.get(position).getMean());
            holder.tvExample.setText(mListVocabulary.get(position).getExample());
            String word = mListVocabulary.get(position).getWord();
            String mean = mListVocabulary.get(position).getMean();
            holder.layout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OneVocabularyActivity.class);
                    intent.putExtra("word", word);
                    intent.putExtra("mean", mean);
                    context.startActivity(intent);
                    Log.d("word", word);
                    Log.d("mean", mean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListVocabulary != null) {
            return mListVocabulary.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWord;
        private TextView tvMean;
        private TextView tvExample;
        private LinearLayout layout_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWord = itemView.findViewById(R.id.word);
            tvMean = itemView.findViewById(R.id.mean);
            tvExample = itemView.findViewById(R.id.example);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }

    public void release() {
        context = null;
    }

//    public class LoadingViewHolder extends RecyclerView.ViewHolder {
//        ProgressBar progressBar;
//
//        public LoadingViewHolder(@NonNull View itemView) {
//            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
//        }
//    }
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_ITEM) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.list_vocabulary_item, parent, false);
//            return new ListVocabularyViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_load_more, parent, false);
//            return new LoadingViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        SuccessVocabulary successVocabulary = mListVocabulary.get(position);
//        String word = mListVocabulary.get(position).getWord();
//        String mean = mListVocabulary.get(position).getMean();
//
//        if (holder instanceof ListVocabularyViewHolder) {
//            ListVocabularyViewHolder listVocabularyViewHolder = (ListVocabularyViewHolder) holder;
//            if (successVocabulary == null) {
//                return;
//            }
//            if (listVocabularyViewHolder != null) {
//                listVocabularyViewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(context, OneVocabularyActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("word", word);
//                        bundle.putString("mean", mean);
//                        intent.putExtra("data", bundle);
//                        context.startActivity(intent);
//                    }
//                });
//            }
//        } else {
//            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
//            loadingViewHolder.progressBar.setIndeterminate(true);
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mListVocabulary.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
//    }
//
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
//
//    @Override
//    public int getItemCount() {
//        if (mListVocabulary != null) {
//            return mListVocabulary.size();
//        }
//        return 0;
//    }
//
//    public class ListVocabularyViewHolder extends RecyclerView.ViewHolder {
//        TextView tvWord;
//        TextView tvMean;
//        TextView tvExample;
//        LinearLayout layout_item;
//
//        public ListVocabularyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvWord = itemView.findViewById(R.id.word);
//            tvMean = itemView.findViewById(R.id.mean);
//            tvExample = itemView.findViewById(R.id.example);
//            layout_item = itemView.findViewById(R.id.layout_item);
//        }
//    }
}
