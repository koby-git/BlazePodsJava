package com.koby.blazepodsjava.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.koby.blazepodsjava.model.Item;

@Database(entities = {Item.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao userDao();
}
