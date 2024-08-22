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

public class CreatePostActivity extends AppCompatActivity {

    private EditText etUserId, etTitle, etBody;
    private Button btnSubmitPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Initialize views
        etUserId = findViewById(R.id.et_user_id);
        etTitle = findViewById(R.id.et_title);
        etBody = findViewById(R.id.et_body);
        btnSubmitPost = findViewById(R.id.btn_submit_post);

        // Set up submit button click listener
        btnSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from the input fields
                int userId = Integer.parseInt(etUserId.getText().toString().trim());
                String title = etTitle.getText().toString().trim();
                String body = etBody.getText().toString().trim();

                // Call the method to create a new post
                createNewPost(userId, title, body);
            }
        });
    }

    private void createNewPost(int userId, String title, String body) {
        Posts newPost = new Posts();
        newPost.setUserId(userId);
        newPost.setTitle(title);
        newPost.setBody(body);

        // Make the POST request
        Call<Posts> call = RetrofitClient.getInstance().getMyApi().createPost(newPost);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.isSuccessful()) {
                    Posts createdPost = response.body();
                    Toast.makeText(CreatePostActivity.this, "Post Created! ID: " + createdPost.getId(), Toast.LENGTH_LONG).show();

                    // Redirect to PostDetailActivity with the post information
                    Intent intent = new Intent(CreatePostActivity.this, PostDetailActivity.class);
                    intent.putExtra("POST", createdPost);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreatePostActivity.this, "Failed to create post", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(CreatePostActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
