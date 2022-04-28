package com.example.englanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.englanguage.adapter.ListTopicAdapter;
import com.example.englanguage.model.topic.Success;
import com.example.englanguage.model.topic.Topic;
import com.example.englanguage.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicActivity extends AppCompatActivity {

    private ImageView listFlipCart, soundTextToSpeech;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ListTopicAdapter adapter;
    private List<Success> postsList = new ArrayList<>();
    private Context context = TopicActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListTopicAdapter(postsList, context);
        recyclerView.setAdapter(adapter);

        listFlipCart = findViewById(R.id.listFlipCart);
        listFlipCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicActivity.this, FlipCardActivity.class);
                startActivity(intent);
            }
        });

        soundTextToSpeech = findViewById(R.id.soundTextToSpeech);
        soundTextToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(TopicActivity.this, TextToSpeechActivity.class);
                startActivity(intent2);
            }
        });

        clickGetTopic();
    }

    public void clickGetTopic() {
        progressBar.setVisibility(View.VISIBLE);
        API.api.getTopics(1).enqueue(new Callback<Topic>() {
            @Override
            public void onResponse(Call<Topic> call, Response<Topic> response) {
                Topic topic = response.body();
                for (int i = 0; i < topic.getSuccess().size(); i++) {
                    Success success = new Success(topic.getSuccess().get(i).getName(),
                            topic.getSuccess().get(i).getSoluong(),
                            topic.getSuccess().get(i).getId());
                    postsList.add(success);
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Topic> call, Throwable t) {
                Toast.makeText(TopicActivity.this, "Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}