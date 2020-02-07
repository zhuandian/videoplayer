package com.example.videoplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.videoplayer.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * desc :
 * author：xiedong
 * date：2020/02/07
 */
public class VideoDao {
    private static VideoDao instance;
    private DataBaseHelper dataBaseHelper = null;
    private SQLiteDatabase db;

    private VideoDao(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
    }

    public static VideoDao getInstance(Context context) {
        if (instance == null) {
            return new VideoDao(context);
        }
        return instance;
    }


    public void addVideo(VideoEntity videoEntity) {
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("path", videoEntity.getPath());
            values.put("thumbPath", videoEntity.getThumbPath());
            values.put("name", videoEntity.getName());
            values.put("duration", videoEntity.getDuration());
            values.put("id", Integer.parseInt(videoEntity.getId()));
            db.insertOrThrow("video", null, values);
        } catch (Exception e) {
        }finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }


    public List<VideoEntity> getVideoList() {
        Cursor cursor = db.query("video", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            List<VideoEntity> videoEntities = new ArrayList<>();
            while (cursor.moveToNext()) {
                VideoEntity videoEntity = new VideoEntity();
                videoEntity.setId(cursor.getInt(cursor.getColumnIndex("id")) + "");
                videoEntity.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
                videoEntity.setName(cursor.getString(cursor.getColumnIndex("name")));
                videoEntity.setPath(cursor.getString(cursor.getColumnIndex("path")));
                videoEntity.setThumbPath(cursor.getString(cursor.getColumnIndex("thumbPath")));
                videoEntities.add(videoEntity);
            }
            return videoEntities;
        }
        return null;
    }

}
