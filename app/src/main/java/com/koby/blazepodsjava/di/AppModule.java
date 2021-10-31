package com.koby.blazepodsjava.di;

import static com.koby.blazepodsjava.util.Constants.API_KEY;
import static com.koby.blazepodsjava.util.Constants.APP_DATABASE;

import android.content.Context;

import androidx.room.Room;

import com.koby.blazepodsjava.R;
import com.koby.blazepodsjava.data.local.AppDatabase;
import com.koby.blazepodsjava.data.remote.MyAppService;
import com.koby.blazepodsjava.repository.Repository;
import com.koby.blazepodsjava.util.Constants;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(
        @ApplicationContext Context context
    ) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                APP_DATABASE
        ).fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient ) {
        return new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public MyAppService provideImdbService(
            Retrofit retrofit
    ) {
        return retrofit.create(MyAppService.class);
    }

    @Singleton
    @Provides
    public Interceptor provideOkHttpNetworkInterceptor(){
        return chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();
            Request newRequest = request.newBuilder().url(url).build();
            return chain.proceed(newRequest);
        };
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLogger() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor okHttpLogger ,
            Interceptor okHttpNetworkInterceptor
    ){
        return new OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .addInterceptor(okHttpNetworkInterceptor)
            .build();
    }

    @Singleton
    @Provides
    public Repository provideRepository(
            AppDatabase appDatabase,
            MyAppService myAppService
    ) {
        return new Repository(appDatabase,myAppService);
    }

    /*
      @Singleton
      @Provides
      fun provideOkHttpNetworkInterceptor(): Interceptor {
          return Interceptor { chain ->
              val newRequest =
                  chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
              chain.proceed(newRequest)
          }
    }*/
}

