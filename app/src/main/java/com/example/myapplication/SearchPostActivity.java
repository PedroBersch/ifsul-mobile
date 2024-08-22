package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.client.RetrofitClient;
import com.example.myapplication.model.Posts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPostActivity extends AppCompatActivity {
    private Button btnPost, btnPut, btnFindBy, btnAll, btnSubmit;
    private EditText etPostId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_post);

        etPostId = findViewById(R.id.et_post_id);

        btnPost = findViewById(R.id.btn_post);
        btnPut = findViewById(R.id.btn_put);
        btnFindBy = findViewById(R.id.btn_findby);
        btnAll = findViewById(R.id.btn_all);
        btnSubmit = findViewById(R.id.btn_submit);
        etPostId = findViewById(R.id.et_post_id);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPostActivity.this, CreatePostActivity.class));
            }
        });

//        btnPut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SearchPostActivity.this, PutActivity.class));
//            }
//        });

        btnFindBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPostActivity.this, SearchPostActivity.class));
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPostActivity.this, MainActivity.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postIdStr = etPostId.getText().toString().trim();

                if (postIdStr.isEmpty()) {
                    Toast.makeText(SearchPostActivity.this, "Please enter a Post ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int postId = Integer.parseInt(postIdStr);
                fetchPostById(postId);
            }
        });
    }

    private void fetchPostById(int postId) {
        Call<Posts> call = RetrofitClient.getInstance().getMyApi().getPostById(postId);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Posts post = response.body();

                    // Start PostDetailActivity and pass the post data
                    Intent intent = new Intent(SearchPostActivity.this, PostDetailActivity.class);
                    intent.putExtra("POST", post);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchPostActivity.this, "Post not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(SearchPostActivity.this, "Error fetching post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}