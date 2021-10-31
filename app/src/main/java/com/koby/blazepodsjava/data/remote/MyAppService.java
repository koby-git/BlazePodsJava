package com.koby.blazepodsjava.data.remote;

import com.koby.blazepodsjava.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAppService {

    @GET("posts")
    Call<List<Item>> getItemsList();
}
