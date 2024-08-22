package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.PostAdapter;
import com.example.myapplication.client.RetrofitClient;
import com.example.myapplication.model.Posts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView superListView;
    Button btnOpenSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superListView = findViewById(R.id.superListView);
        btnOpenSearch = findViewById(R.id.btn_open_search);  // Initialize the button inside onCreate

        getPosts();

        btnOpenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchPostActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPosts();
    }

    private void getPosts() {
        Call<List<Posts>> call = RetrofitClient.getInstance().getMyApi().getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                List<Posts> postsList = response.body();
                System.out.println("tamanho da lista: " + postsList.size());
                PostAdapter adapter = new PostAdapter(MainActivity.this, postsList);
                superListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
            }
        });
    }
}
