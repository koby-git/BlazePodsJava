package com.koby.blazepodsjava.model;

import static com.koby.blazepodsjava.util.Constants.APP_TABLE_NAME;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = APP_TABLE_NAME)
public class Item {
    @PrimaryKey
    public int id;
    public String title;
    public String body;
}
