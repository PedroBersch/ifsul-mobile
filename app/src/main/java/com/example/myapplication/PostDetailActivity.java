package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.adapter.PostDetailAdapter;
import com.example.myapplication.model.Posts;

public class PostDetailActivity extends AppCompatActivity {

    private Button btnPost, btnPut, btnFindBy, btnAll, btnBackToMain;
    private TextView tvTitle, tvBody;
    private ListView superListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        btnPost = findViewById(R.id.btn_post);
        btnPut = findViewById(R.id.btn_put);
        btnFindBy = findViewById(R.id.btn_findby);
        btnAll = findViewById(R.id.btn_all);
        btnBackToMain = findViewById(R.id.btn_back_to_main);

        tvTitle = findViewById(R.id.tv_title);
        tvBody = findViewById(R.id.tv_body);
        superListView = findViewById(R.id.superListView);  // Initialize the ListView

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("POST")) {
            Posts post = (Posts) intent.getSerializableExtra("POST");

            // Set title and body
            tvTitle.setText(post.getTitle());
            tvBody.setText(post.getBody());

            // Assuming PostDetailAdapter is for more detailed information
            PostDetailAdapter adapter = new PostDetailAdapter(this, post);
            superListView.setAdapter(adapter);
        }

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostDetailActivity.this, MainActivity.class));
            }
        });
        btnFindBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostDetailActivity.this, SearchPostActivity.class));
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostDetailActivity.this, MainActivity.class));
            }
        });
    }
}
