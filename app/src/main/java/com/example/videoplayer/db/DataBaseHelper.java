package com.example.videoplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * desc :
 * author：xiedong
 * date：2020/02/07
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "history.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists video(" +
                "id integer primary key autoincrement," +
                "path text," +
                "thumbPath text," +
                "duration text," +
                "name)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
