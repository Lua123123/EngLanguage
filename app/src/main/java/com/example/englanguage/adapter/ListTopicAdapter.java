package com.example.englanguage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englanguage.R;
import com.example.englanguage.model.topic.Success;

import java.util.List;

public class ListTopicAdapter extends RecyclerView.Adapter<ListTopicAdapter.ViewHolder> {
    private List<Success> postsList;

    public ListTopicAdapter(List<Success> postsList) {
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Success success = postsList.get(position);
        holder.tvTitle.setText(success.getName());
        holder.tvMount.setText(success.getSoluong().toString());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvMount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.topic);
            tvMount = itemView.findViewById(R.id.amount);
        }
    }
}
