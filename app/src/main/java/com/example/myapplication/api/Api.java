package com.example.myapplication.api;

import com.example.myapplication.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<Posts>> getPosts();
    @GET("posts/{id}")
    Call<Posts> getPostById(@Path("id") int id);
}