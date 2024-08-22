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

    private ListView superListView;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        superListView = findViewById(R.id.superListView);
        btnReturn = findViewById(R.id.btn_return);

        // Retrieve the post data from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("POST")) {
            Posts post = (Posts) intent.getSerializableExtra("POST");

            // Set up the adapter to display the post details
            PostDetailAdapter adapter = new PostDetailAdapter(this, post);
            superListView.setAdapter(adapter);
        }

        // Set up the button to return to the main activity
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to return to the previous one (MainActivity)
                finish();
            }
        });
    }
}