package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Posts;

import java.util.List;

public class PostDetailAdapter extends ArrayAdapter<Posts> {

    public PostDetailAdapter(Context context, Posts post) {
        super(context, 0, List.of(post)); // Wrap the single post in a list
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post_detail, parent, false);
        }

        Posts post = getItem(position);

        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvBody = convertView.findViewById(R.id.tv_body);

        if (post != null) {
            tvTitle.setText(post.getTitle());
            tvBody.setText(post.getBody());
        }

        return convertView;
    }
}