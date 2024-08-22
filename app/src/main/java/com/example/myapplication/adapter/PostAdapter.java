package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.Posts;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Posts> {

    private final Context context;
    private final List<Posts> postsList;

    public PostAdapter(Context context, List<Posts> postsList) {
        super(context, 0, postsList);
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate the layout for each list item
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_post, parent, false);
        }

        // Get the current post
        Posts currentPost = postsList.get(position);

        // Find and populate the title TextView
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        titleTextView.setText("TITULO: " + currentPost.getTitle());

        // Find and populate the body TextView
        TextView bodyTextView = convertView.findViewById(R.id.bodyTextView);
        bodyTextView.setText(currentPost.getBody());

        return convertView;
    }
}
