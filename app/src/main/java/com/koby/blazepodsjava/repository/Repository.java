package com.koby.blazepodsjava.repository;

import androidx.lifecycle.LiveData;

import com.koby.blazepodsjava.data.local.AppDao;
import com.koby.blazepodsjava.data.local.AppDatabase;
import com.koby.blazepodsjava.data.remote.MyAppService;
import com.koby.blazepodsjava.model.Item;
import com.koby.blazepodsjava.util.AppExecutor;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private AppDao mAppDao;
    private MyAppService mRetrofitService;

    @Inject
    public Repository(AppDatabase appDatabase,MyAppService retrofitService) {
        mAppDao = appDatabase.userDao();
        mRetrofitService = retrofitService;
    }

    public void callInitMovieList(){
        Call<List<Item>> movieList = mRetrofitService.getItemsList();
        movieList.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                System.out.println("+++++++++++++++++");
                System.out.println(response.body());
                System.out.println("+++++++++++++++++");
                AppExecutor.getInstance().diskIO().execute(() -> {
                    mAppDao.insertAll(response.body());
                });

            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<List<Item>> getAllMovieList(){
        return mAppDao.getAllItems();
    }



}
