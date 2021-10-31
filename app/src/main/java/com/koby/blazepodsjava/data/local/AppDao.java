package com.koby.blazepodsjava.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.koby.blazepodsjava.model.Item;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Item> item);

    @Query("SELECT * FROM APP_TABLE")
    public LiveData<List<Item>> getAllItems();

    /*
        @Query("DELETE FROM app_table WHERE x = 1")
        suspend fun deleteDiscoverMovies()
    */

}